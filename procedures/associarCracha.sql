--drop procedure if exists associarCracha(int, varchar(10), varchar(20));

create or replace procedure associarCracha(jogadorId int, idJogo varchar(10), crachaNome varchar(20))
language plpgSQL as
$$
declare 
	totalPoints int;
	jogoNome varchar(20);
begin
	--set transaction isolation level repeatable read;

	if not exists(select * from Jogador where id = jogadorId) then
		raise exception 'Jogador com o id % não existe.',jogadorId;
	end if;

	if not exists(select * from Cracha where nome = crachaNome) then
		raise exception 'Cracha % não existe.', crachaNome;
	end if;	

    if not exists(select * from Jogo where id = idJogo) then
		raise exception 'Jogo com o id % não existe.', idJogo;
	end if;
     
    select limiteDePontos
    into totalPoints
	from Cracha
    where nome = crachaNome;
		
    select nome
    into jogoNome
	from Jogo
    where id = idJogo;
	
    if((select totalPontos 
	from PontosJogoPorJogador(jogoNome)
	where idJogador = jogadorId
	) < totalPoints) then
        raise exception 'Jogador com id % nao tem pontos suficientes para o rank %', jogadorId, crachaNome;
    end if;

	begin
		insert into Tem (idJogador, nomeCracha, nomeJogo)
		values (jogadorId, crachaNome, jogoNome);
		raise notice 'cracha atribuido';
		exception
			when others then
				NULL;
	end;
end;
$$;

select * from tem;

call associarCracha(1, '1', 'TetrisRank4');