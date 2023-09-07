--drop function totalPontosJogador(integer);

create or replace function totalPontosJogador(jogadorId int)
returns int
language plpgSQL as
$$
declare 
	totalPontos integer;
	temporario integer;
begin
	if not exists (select * from Jogador where id = jogadorId) then
		raise exception 'Jogador com o id % não existe.',jogadorId;
	end if;
	
	select coalesce(sum(pontuacao),0)
	into totalPontos
	from Jogar 
	where idJogador = jogadorId;
	
	select coalesce(sum(pontuacao),0)
	into temporario
	from Normal
	where idJogador = jogadorId;
	
	totalPontos = totalPontos + temporario;
	
	insert into EstatisticaJogador (idJogador)
	values (jogadorId)
	on conflict (idJogador) do nothing;
	
	update EstatisticaJogador
	set totalPontosJogos = totalPontos
	where idJogador = jogadorId;

	return totalPontos;
end;
$$;

select totalPontosJogador(6);