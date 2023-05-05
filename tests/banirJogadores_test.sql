create or replace procedure banirJogadores_test()
language plpgSQL as
$$
declare
	jogadorId int;
begin 
	
	insert into Regiao (nome)
	values ('Africa');

	insert into Jogador (estado, userName, email, nomeRegiao)
	values ('Ativo', 'UserTest', 'Test123@gmail.com', 'Africa')
	returning id into jogadorId;
	
  
	delete from jogadorTotalInfo where id = jogadorId;
	
	IF (SELECT estado FROM Jogador WHERE id = jogadorId) = 'Banido' THEN
		raise notice 'Resultado banirJogadores_test: OK';
	ELSE
		raise notice 'Resultado banirJogadores_test: FAIL';
   	END IF;
	rollback;
end;
$$;

do
$$ 
begin
	set transaction isolation level repeatable read;
	call banirJogadores_test();
end;
$$;