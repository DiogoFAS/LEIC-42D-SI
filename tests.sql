--tests

create or replace procedure test_totalPontosJogador1() --Jogador existente mas nenhuma partida.
language plpgSQL as
$$
declare 
	JogadorId int;
	totalFunc int;
	totalEstatistica int;
begin
	insert into Regiao (nome) 
	values ('regiaoTest');
	--on conflict (nome) do nothing;
	
	insert into Jogador (estado, userName, email, nomeRegiao)
	values ('Ativo', 'Test123', 'Test123@gmail.com', 'regiaoTest')
	returning id into JogadorId;
	--on conflict (userName, email) do nothing;
	
	select totalPontosJogador(JogadorId)
	into totalFunc;

	select totalPontosJogos 
	into totalEstatistica 
	from Estatistica 
	where idJogador = jogadorId;

	if totalFunc = totalEstatistica and totalFunc = 0 then
		raise notice 'Teste1: Obter total de pontos de jogador sem partidas: Resultado OK';
	else
		raise notice 'Teste1: Obter total de pontos de jogador sem partidas: Resultado FAIL';
	end if;
	
	rollback;
end;
$$;


create or replace procedure test_totalPontosJogador2() --Jogador existente mas apenas 1 Partida.
language plpgSQL as
$$
declare 
	JogadorId int;
	PartidaId int;
	totalFunc int;
	totalEstatistica int;
begin
	insert into Regiao (nome) 
	values ('regiaoTest');
	--on conflict (nome) do nothing;
	
	insert into Jogador (estado, userName, email, nomeRegiao)
	values ('Ativo', 'Test123', 'Test123@gmail.com', 'regiaoTest')
	returning id into JogadorId;
	--on conflict (userName, email) do nothing;
	
	insert into Partida (nomeJogo, dataFim)
	values ('Pacman', '2023-04-30 19:40:00') 
	returning id into PartidaId;
	
	insert into Normal (idPartida, dificuldade, idJogador, pontuacao)
	values (PartidaId, 3, JogadorId, 600);
	
	select totalPontosJogador(JogadorId)
	into totalFunc;

	select totalPontosJogos 
	into totalEstatistica 
	from Estatistica 
	where idJogador = jogadorId;

	if totalFunc = totalEstatistica then
		raise notice 'Teste2: Obter total de pontos de jogador sem partidas: Resultado OK';
	else
		raise notice 'Teste2: Obter total de pontos de jogador sem partidas: Resultado FAIL';
	end if;
	
	rollback;
end;
$$;


create or replace procedure test_totalPontosJogador3() --Jogador existente.
language plpgSQL as
$$
declare 
	totalFunc int;
	invalidIdJogador int default 0;
	msg text;
	correctMsg text;
begin	
	begin 
		select totalPontosJogador(invalidIdJogador)
		into totalFunc;
		exception 
			when others then
				get stacked diagnostics msg = MESSAGE_TEXT;
				
		--correctMsg = 'Jogador com o id % não existe.',invalidIdJogador;
				
		if msg = 'Jogador com o id 0 não existe.' then
			raise notice 'Teste3: Obter total de pontos de jogador inexistente: Resultado OK';
		else
			raise notice 'Teste3: Obter total de pontos de jogador inexistente: Resultado FAIL';
		end if;
	end;
	rollback;
end;
$$;


call test_totalPontosJogador1();
call test_totalPontosJogador2();
call test_totalPontosJogador3();

---------------------------------------------------------------------------------------------------------


create or replace procedure test_iniciarConversa1()-- iniciar conversa com um jogador existente.
language plpgSQL as
$$
declare
	JogadorId int;
	idConversa int;
begin 
	set transaction isolation level read uncommitted;
	insert into Regiao (nome) 
	values ('regiaoTest');
	--on conflict (nome) do nothing;
	
	insert into Jogador (estado, userName, email, nomeRegiao)
	values ('Ativo', 'Test123', 'Test123@gmail.com', 'regiaoTest')
	returning id into JogadorId;
	--on conflict (userName, email) do nothing;
	
	call iniciarConversa(JogadorId, 'nomeDaConversa', idConversa); 
		
	if not exists (select * from Conversa where id = idConversa) then
		raise notice 'Teste1: iniciar conversa com um jogador existente: Resultado FAIL';
	else
		raise notice 'Teste1: iniciar conversa com um jogador existente: Resultado OK';
	end if;
	rollback;
end;
$$;


create or replace procedure test_iniciarConversa2()-- iniciar conversa com um jogador inexistente.
language plpgSQL as
$$
declare
	invalidIdJogador int default 0;
	idConversa int;
	msg text;
begin 
	set transaction isolation level read uncommitted;
	call iniciarConversa(invalidIdJogador, 'nomeDaConversa', idConversa);
	exception 
		when others then
			get stacked diagnostics msg = MESSAGE_TEXT;
				
	--correctMsg = cast(('Jogador com o id % não existe.',invalidIdJogador) as text);
	
	raise notice '%', msg;
	
	if msg = 'Jogador com o id 0 não existe.' then
		raise notice 'Teste1: Iniciar conversa com um jogador inexistente: Resultado OK';
	else
		raise notice 'Teste1: Iniciar conversa com um jogador inexistente: Resultado FAIL';
	end if;
rollback;
end;
$$;

set transaction isolation level read uncommitted;
call test_iniciarConversa1();
set transaction isolation level read uncommitted;
call test_iniciarConversa2();

---------------------------------------------------------------------------------------------------------


create or replace procedure test_juntarConversa1() --
language plpgSQL as
$$
declare
	conversaId Int;
begin 
	set transaction isolation level read uncommitted;

	call iniciarConversa()


































