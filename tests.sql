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

	if totalFunc = totalEstatistica then
		raise notice 'Teste1: Obter total de pontos de jogador sem partidas: Resultado OK';
	else
		raise notice 'Teste1: Obter total de pontos de jogador sem partidas: Resultado FAIL';
	end if;
	
	rollback;
end;
$$;


create or replace procedure test_totalPontosJogador2() --Jogador existente 1 Partida.
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
		raise notice 'Teste1: Obter total de pontos de jogador sem partidas: Resultado OK';
	else
		raise notice 'Teste1: Obter total de pontos de jogador sem partidas: Resultado FAIL';
	end if;
	
	rollback;
end;
$$;


create or replace procedure test_totalPontosJogador3() --Jogador existente.
language plpgSQL as
$$
declare 
	totalFunc int;
	totalEstatistica int;
begin	
	select totalPontosJogador(0)
	into totalFunc;

	select totalPontosJogos 
	into totalEstatistica 
	from Estatistica 
	where idJogador = jogadorId;

	if totalFunc = totalEstatistica then
		raise notice 'Teste1: Obter total de pontos de jogador sem partidas: Resultado OK';
	else
		raise notice 'Teste1: Obter total de pontos de jogador sem partidas: Resultado FAIL';
	end if;
	
	rollback;
end;
$$;


call test_totalPontosJogador1();
call test_totalPontosJogador2();

--------------------------------------------------------------------------------------------


create or replace procedure test_iniciarConversa()-- iniciar conversa com um jogador existente.
language plpgSQL as
$$
declare
	JogadorId int;
	idConversa int;
begin 
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

call test_iniciarConversa();

