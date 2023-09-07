create or replace procedure PontosJogoPorJogador_test() --Jogador existente mas nenhuma partida.
language plpgSQL as
$$
declare 
	jogoNome varchar(20);
    jogadorId int;
	totalPoints int;
begin
	insert into Regiao (nome) 
	values ('regiaoTest');
	
	insert into Jogador (estado, userName, email, nomeRegiao)
	values ('Ativo', 'Test123', 'Test123@gmail.com', 'regiaoTest')
	returning id into jogadorId;

    insert into Jogo (nome, id, URL)
	values ('Fortnite', '1', 'fortnite.com')
	returning nome into jogoNome;
	
	select totalPontos from PontosJogoPorJogador(jogoNome)
	into totalPoints
    where idJogador = jogadorId;

	if (coalesce(totalPoints, 0) = 0) then
		raise notice 'PontosJogoPorJogador_test: Obter pontos dum jogador sem jogos jogados: Resultado OK';
	else
		raise notice 'PontosJogoPorJogador_test: Obter pontos dum jogador sem jogos jogados: Resultado FAIL';
	end if;
	
	rollback;
end;
$$;

do
$$ 
begin 
call PontosJogoPorJogador_test();
end;
$$;