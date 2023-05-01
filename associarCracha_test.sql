create or replace procedure associarCracha_test()
language plpgSQL as
$$
declare
	JogadorId int;
	idConversa int;
	idJogo varchar(10);
	nomeJogo varchar(20);
begin 
	insert into Regiao (nome) 
	values ('regiaoTest');
	
	insert into Jogador (estado, userName, email, nomeRegiao)
	values ('Ativo', 'Test123', 'Test123@gmail.com', 'regiaoTest')
	returning id into JogadorId;
	
    insert into Jogo (nome, id, URL)
	values ('Fortnite', '1', 'fortnite.com')
	returning id, nome into idJogo, nomeJogo;

	insert into Cracha (nome, nomeJogo, limiteDePontos)
    values ('FortniteMaster', nomeJogo, 1000);

    insert into Partida (id, nomeJogo, dataFim)
    values (5, nomeJogo, '2023-04-30 19:40:00');

    insert into Jogar (idPartida, idJogador, pontuacao)
    values (5, 1, 450);

	call associarCracha(JogadorId, idJogo, 'FortniteMaster');
	raise notice 'ele tem: %', ((select nomeCracha from Tem where idJogador = JogadorId));

	if not exists (select * from Tem where idJogador = JogadorId) then
		raise notice 'associarCracha_test: Associar Cracha a um jogador: Resultado OK';
	else
		raise notice 'associarCracha_test: Associar Cracha a um jogador: Resultado FAIL';
	end if;
	rollback;
end;
$$;

call associarCracha_test();