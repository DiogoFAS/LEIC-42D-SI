create or replace procedure juntarConversa_test1()--Jogador existente junta-se a uma conversa inexistente.
language plpgSQL as 
$$
declare 
	jogadorId int;
	msg text;
begin 
	call criarJogador('Test', 'Test123@gmail.com', 'regiaoTest');
	
	select id into jogadorId from Jogador where email = 'Test123@gmail.com';
	
	begin 
		call juntarConversa(jogadorId, 0);
		exception
			when others then 
				get stacked diagnostics msg = MESSAGE_TEXT;
			
		if msg = 'Conversa com o id 0 não existe.' then 
			raise notice 'Teste1: Jogador existente junta-se a uma conversa inexistente: Resultado OK';
		else
			raise notice 'Teste1: Jogador existente junta-se a uma conversa inexistente: Resultado FAIL';
		end if;
	end;
	rollback;
end;
$$;


create or replace procedure juntarConversa_test2()--Jogador existente junta-se a uma conversa existente.
language plpgSQL as 
$$
declare 
	jogadorId1 int;
	jogadorId2 int;
	conversaId int;
begin
	set transaction isolation level read uncommitted;
	call criarJogador('Test1', 'Test1@gmail.com', 'regiaoTest');
	
	call criarJogador('Test2', 'Test2@gmail.com', 'regiaoTest');
	
	select id into jogadorId1 from Jogador where email = 'Test1@gmail.com';
	select id into jogadorId2 from Jogador where email = 'Test2@gmail.com';
	
	call iniciarConversa(jogadorId1, 'ConversaTest', conversaId);

	call juntarConversa(jogadorId2, conversaId);

	if not exists (select * from Conversa where idJogador = jogadorId2) then
		raise notice 'Teste2: Jogador existente junta-se a uma conversa existente: Resultado FAIL';
	else
		raise notice 'Teste2: Jogador existente junta-se a uma conversa existente: Resultado OK';
	end if;
	rollback;
end;
$$;


create or replace procedure juntarConversa_test3()--Jogador existente juntar-se a uma conversa que já pertence.
language plpgSQL as
$$
declare 
	jogadorId1 int;
	jogadorId2 int;
	conversaId int;
begin 
	call criarJogador('Test1', 'Test1@gmail.com', 'regiaoTest');
	 
	call criarJogador('Test2', 'Test2@gmail.com', 'regiaoTest');
	
	select id into jogadorId1 from Jogador where email = 'Test1@gmail.com';
	select id into jogadorId2 from Jogador where email = 'Test2@gmail.com';
	
	call iniciarConversa(jogadorId1, 'ConversaTest', conversaId);

	call juntarConversa(jogadorId2, conversaId);

	call juntarConversa(jogadorId2, conversaId);
	
	if not exists (select * from Conversa where idJogador = jogadorId2) then
		raise notice 'Teste2: Jogador existente junta-se a uma conversa existente: Resultado FAIL';
	else
		raise notice 'Teste2: Jogador existente junta-se a uma conversa existente: Resultado OK';
	end if;
	rollback;
end;
$$;


create or replace procedure juntarConversa_test4()--Jogador inexistente junta-se a uma conversa.
language plpgSQL as 
$$
declare 
	invalidIdJogador int default 0;
	jogadorId int;
	conversaId int;
	msg text;
begin
	call criarJogador('Test1', 'Test1@gmail.com', 'regiaoTest');
	
	select id into jogadorId from Jogador where email = 'Test1@gmail.com';
	
	call iniciarConversa(jogadorId, 'ConversaTest', conversaId);

	begin
		call juntarConversa(invalidIdJogador, conversaId);
		exception
			when others then 
				get stacked diagnostics msg = MESSAGE_TEXT;
	
		if msg = 'Jogador com o id 0 não existe.' then 
			raise notice 'Teste1: Jogador existente junta-se a uma conversa inexistente: Resultado OK';
		else
			raise notice 'Teste1: Jogador existente junta-se a uma conversa inexistente: Resultado FAIL';
		end if;
	end;
	rollback;
end;
$$;

set transaction isolation level read uncommitted;
call juntarConversa_test1();
set transaction isolation level read uncommitted;
call juntarConversa_test2();
set transaction isolation level read uncommitted;
call juntarConversa_test3();
set transaction isolation level read uncommitted;
call juntarConversa_test4();