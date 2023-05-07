--drop function totalJogosJogador(integer);

create or replace function totalJogosJogador(jogadorId int)
returns int
language plpgSQL as
$$
declare 
	totalJogos integer;
begin
	if not exists (select * from Jogador where id = jogadorId) then
		raise exception 'Jogador com o id % não existe.',jogadorId;
	end if;
	
	select coalesce(count(distinct nomeJogo), 0)
	into totalJogos
	from Jogar
	inner join Normal
	on Jogar.idJogador = Normal.idJogador
	where Jogar.idJogador = jogadorId and Normal.idJogador = jogadorId;
	
	insert into EstatisticaJogador (idJogador)
	values (jogadorId)
	on conflict (idJogador) do nothing;
	
	update EstatisticaJogador
	set nrJogos = totalJogos
	where idJogador = jogadorId;

	return totalJogos;
end;
$$;

select totalJogosJogador(3); -- instruction that tests the function