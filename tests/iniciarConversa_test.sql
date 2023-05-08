create or replace procedure iniciarConversa_test1()-- iniciar conversa com um jogador existente.
language plpgSQL as
$$
declare
	JogadorId int;
	idConversa int;
begin
	call criarJogador('Test', 'Test123@gmail.com', 'regiaoTest');
	
	select id into JogadorId from Jogador where email = 'Test123@gmail.com';
	
	call iniciarConversa(JogadorId, 'nomeDaConversa', idConversa);
	
	if not exists (select * from Conversa where id = idConversa) then
		raise notice 'Teste1: iniciar conversa com um jogador existente: Resultado FAIL';
	else
		raise notice 'Teste1: iniciar conversa com um jogador existente: Resultado OK';
	end if;
end;
$$;


create or replace procedure iniciarConversa_test2()-- iniciar conversa com um jogador inexistente.
language plpgSQL as
$$
declare
	invalidIdJogador int default 0;
	idConversa int;
	msg text;
begin
	call iniciarConversa(invalidIdJogador, 'nomeDaConversa', idConversa);
	exception 
		when others then
			get stacked diagnostics msg = MESSAGE_TEXT;
			
	if msg = 'Jogador com o id 0 não existe.' then
		raise notice 'Teste2: Iniciar conversa com um jogador inexistente: Resultado OK';
	else
		raise notice 'Teste2: Iniciar conversa com um jogador inexistente: Resultado FAIL';
	end if;
end;
$$;

select * from Jogador;
select * from conversa;

set transaction isolation level read uncommitted;
do
$$ 
begin 
	call iniciarConversa_test1();
	call iniciarConversa_test2();
	rollback;
end;
$$;