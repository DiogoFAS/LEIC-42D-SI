/*
  Autor: Walter Vieira (2023/04/28)
  
  Foi detetado que o postgresql gera um erro 25001 com a mensagem 
  "SET TRANSACTION ISOLATION LEVEL must be called before any query" quando dentro dum stored procedure
  se executa SET TRANSACTION ISOLATION LEVEL com um nível de isolamento diferente do "default", embora
  isso não devesse acontecer pelo que é indicado na documentação (https://www.postgresql.org/docs/15/sql-set-transaction.html)
  ond é dito: "The transaction isolation level cannot be changed after the first query or data-modification
  statement (SELECT, INSERT, DELETE, UPDATE, FETCH, or COPY) of a transaction has been executed".
  Na tentativa de resolver este problema, a melhor possibilidade que encontrei foi a que é apresentada
  no ponto 2.
  No ponto 1 é mostrada a situação do erro descrito.
  De notar que o ideal seria em cada zona de código definirmos o nível de isolamento mais adequado, mas isso
  não é possível com as limitações do postgresql.
  
*/

-- ********************* ponto 1: Caraterização do erro ****************************
-- 
drop table if exists t;
create table t(i int primary key, j varchar(10));

SET default_transaction_isolation = 'read committed';


-- pretende-se implementar um SP corresponbdente a um processo x com a funcionalidade de 
-- inserir um registo na tabela t.
-- Foi seguido o padrão indicado nas aulas de separação enbtre a lógica e o controlo transacional
-- Também se considerou (apenas para ilustração do problema) que o nível de isolamento pretendido 
-- é repeatable read

-- Implementação da lógica
create or replace procedure xLogica(a integer, b varchar(10))
language plpgsql as
$$
begin
    raise notice 'Nível de isolamento = %',current_setting('transaction_isolation'); -- só para teste
	insert into t values (a,b);
	-- haverá exceção se for violada a chave primária
end; 
$$;


-- O controlo transacional (com o nome do processo)
create or replace procedure x(a integer, b varchar(10))
language plpgsql as
$$
declare 
       code char(5) default '00000';
	   msg text;
begin
    set transaction isolation level repeatable read; -- dá erro com qualquer nivel exceto o default

	call xLogica(a,b);
	exception
			when others then
				raise notice 'Ocorreu algum erro';
				GET stacked DIAGNOSTICS  
                          code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;   
				raise notice 'code=%, msg=%',code,msg;
				rollback;			
	
end;
$$;

--teste:
call x(1,'aa');
/*
NOTICE:  Ocorreu algum erro
NOTICE:  code=25001, msg=SET TRANSACTION ISOLATION LEVEL must be called before any query
*/

-- ********************* ponto 1: Solução proposta ****************************
-- 
drop table if exists t;
create table t(i int primary key, j varchar(10));

SET default_transaction_isolation = 'read committed';

-- Implementação da lógica
create or replace procedure xLogica(a integer, b varchar(10))
language plpgsql as
$$
begin
    raise notice 'Nível de isolamento = %',current_setting('transaction_isolation'); -- só para teste
	insert into t values (a,b);
	-- haverá exceção se for violada a chave primária
end; 
$$;


-- O controlo transacional separado do nível de isolamento
create or replace procedure xTrans(a integer, b varchar(10))
language plpgsql as
$$
declare 
       code char(5) default '00000';
	   msg text;
begin
	call xLogica(a,b);
	exception
			when others then
				raise notice 'Ocorreu algum erro';
				GET stacked DIAGNOSTICS  
                          code = RETURNED_SQLSTATE, msg = MESSAGE_TEXT;   
				raise notice 'code=%, msg=%',code,msg;
				rollback;			
	
end;
$$;


-- O controlo do nível de isolamento (com o nome do processo)
-- Notar que este SP não pode usasr EXCEPTION, poque, se o fizer, não pode realizar o rollback ou commit 
-- inicial
create or replace procedure x(a integer, b varchar(10)) -- deverá ter o nome do processo de negócio
language plpgsql as
$$

begin
    commit; --rollback; -- ou commit. Termina transação corrente e inicia outra
	set transaction isolation level repeatable read;
	call xTrans(a,b);		
	
end;
$$;

-- teste
call x(11,'aa');
select * from t;
call x(2,'bb');
select * from t;
call x(1,'aa');
select * from t;
