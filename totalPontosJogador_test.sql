create or replace procedure totalPontosJogador_test1() --Jogador existente mas nenhuma partida.
language plpgSQL as
$$
declare 
	JogadorId int;
	totalFunc int;
	totalEstatistica int;
begin
	
	call criarJogador('Test', 'Test123@gmail.com', 'regiaoTest');
	
	select id into JogadorId from Jogador where email = 'Test123@gmail.com';
	
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


create or replace procedure totalPontosJogador_test2() --Jogador existente mas apenas 1 Partida Normal.
language plpgSQL as
$$
declare 
	JogadorId int;
	PartidaId int;
	totalFunc int;
	totalEstatistica int;
begin
	
	call criarJogador('Test', 'Test123@gmail.com', 'regiaoTest');
	
	select id into JogadorId from Jogador where email = 'Test123@gmail.com';
	
	insert into Partida (id, nomeJogo, dataFim)
	values (0,'Pacman', '2023-10-30 19:40:00') 
	returning id into PartidaId;
	
	insert into Normal (idPartida, nomeJogo, dificuldade, idJogador, pontuacao)
	values (PartidaId, 'Pacman', 3, JogadorId, 600);
	
	select totalPontosJogador(JogadorId)
	into totalFunc;

	select totalPontosJogos 
	into totalEstatistica 
	from Estatistica 
	where idJogador = jogadorId;

	if totalFunc = totalEstatistica then
		raise notice 'Teste2: Obter total de pontos de jogador com 1 partida normal: Resultado OK';
	else
		raise notice 'Teste2: Obter total de pontos de jogador com 1 partida normal: Resultado FAIL';
	end if;
	
	rollback;
end;
$$;


create or replace procedure totalPontosJogador_test3()
language plpgSQL as 
$$
declare 
	JogadorId int;
	PartidaIdNormal int;
	PartidaIdMulti int;
	totalFunc int;
	totalEstatistica int;
begin

	call criarJogador('Test', 'Test123@gmail.com', 'regiaoTest');
	
	select id into JogadorId from Jogador where email = 'Test123@gmail.com';
	
	insert into Partida (id, nomeJogo, dataFim)
	values (0, 'Pacman', '2023-10-30 19:40:00') 
	returning id into PartidaIdNormal;
	
	insert into Normal (idPartida, nomeJogo, dificuldade, idJogador, pontuacao)
	values (PartidaIdNormal, 'Pacman', 3, JogadorId, 600);
	
	insert into Partida (id, nomeJogo, dataFim)
	values (-1, 'Pacman', '2023-10-30 19:40:00') 
	returning id into PartidaIdMulti;
	
	insert into multiJogador (idPartida, nomeJogo, estado, nomeRegiao)
	values (PartidaIdMulti, 'Tetris', 'Terminada', 'regiaoTest');
	
	insert into Jogar (idPartida, idJogador, pontuacao)
	values (PartidaIdMulti, JogadorId, 300);
	
	select totalPontosJogador(JogadorId)
	into totalFunc;
	
	select totalPontosJogos 
	into totalEstatistica 
	from Estatistica 
	where idJogador = jogadorId;

	if totalFunc = totalEstatistica then
		raise notice 'Teste3: Obter total de pontos de jogador com 1 partida normal e 1 Partida MultiJogador: Resultado OK';
	else
		raise notice 'Teste3: Obter total de pontos de jogador com 1 partida normal e 1 Partida MultiJogador: Resultado FAIL';
	end if;
	
	rollback;

end;
$$;


create or replace procedure totalPontosJogador_test4() --Jogador inexistente.
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
			raise notice 'Teste4: Obter total de pontos de jogador inexistente: Resultado OK';
		else
			raise notice 'Teste4: Obter total de pontos de jogador inexistente: Resultado FAIL';
		end if;
	end;
	rollback;
end;
$$;


call totalPontosJogador_test1();
call totalPontosJogador_test2();
call totalPontosJogador_test3();
call totalPontosJogador_test4();