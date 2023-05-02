--drop function if exists PontosJogoPorJogador;

create or replace function PontosJogoPorJogador(jogoNome varchar(20))
returns table(idJogador int, totalPontos int)
language plpgSQL as
$$
begin
	
	if not exists (select * from Jogo where nome = jogoNome) then
		raise exception 'Jogo % não existe.', jogoNome;
	end if;

    return query select Normal.idJogador, Normal.pontuacao
	from Normal
	where nomeJogo = jogoNome
	union all 	
	select Jogar.idJogador, Jogar.pontuacao
	from Jogar 
	inner join Partida on Jogar.idPartida = Partida.id
	where Partida.nomeJogo = jogoNome;

end;
$$;

select * from PontosJogoPorJogador('SpaceInv');