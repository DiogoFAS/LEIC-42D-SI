create or replace procedure atribuicaoCracha_test1()--Atribuição do cracha com menor pontos requeridos com uma paritda normal.
language plpgSQL as 
$$
declare 
    jogadorId int;
begin 

    call criarJogador('test', 'test@gmail.com', 'regiaoTest');
	
	select id from Jogador where userName = 'test' into jogadorId;

    insert into Partida(id, nomeJogo, dataFim)
    values (0, 'Tetris', '2023-10-8 14:30');

    insert into Normal(idPartida, nomeJogo, dificuldade, idJogador, pontuacao)
    values (0, 'Tetris', 3, jogadorId, 150);
	
    if not exists (select * from Tem where idJogador = jogadorId) then
        raise notice 'Test1: Atribuição do cracha com menor pontos requeridos com uma paritda normal: Resultado FAIL';
    else
        raise notice 'Test1: Atribuição do cracha com menor pontos requeridos com uma paritda normal: Resultado OK';
    end if;
    rollback;
end;
$$;


create or replace procedure atribuicaoCracha_test2()--Atribuição do cracha com menor pontos requeridos com uma partida MultiJogador.
language plpgSQL as 
$$
declare 
    jogadorId int;
begin 

    call criarJogador('test', 'test@gmail.com', 'regiaoTest');
	
	select id from Jogador where userName = 'test' into jogadorId;

    insert into Partida(id, nomeJogo, dataFim)
    values (0, 'Tetris', '2023-10-8 14:30');
	
    insert into MultiJogador(idPartida, nomeJogo, estado, nomeRegiao)
    values (0, 'Tetris', 'Por Iniciar', 'regiaoTest');

    insert into Jogar(idPartida, nomeJogo, idJogador, pontuacao)
    values (0, 'Tetris', jogadorId, 150);
	
	update MultiJogador set estado = 'Terminada' where idPartida = 4;
	
    if not exists (select * from Tem where idJogador = jogadorId) then
        raise notice 'Test2: Atribuição do cracha com menor pontos requeridos com uma partida MultiJogador: Resultado FAIL';
    else
        raise notice 'Test2: Atribuição do cracha com menor pontos requeridos com uma partida MultiJogador: Resultado OK';
    end if;
    rollback;
end;
$$;

do
$$
begin 
	call atribuicaoCracha_test1();
	call atribuicaoCracha_test2();
end;
$$;