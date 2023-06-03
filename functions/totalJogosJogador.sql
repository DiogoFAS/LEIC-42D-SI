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
	from (select nomeJogo from Jogar
	where Jogar.idJogador = jogadorId
	union
	select nomeJogo from Normal
	where Normal.idJogador = jogadorId
	) as A;
	
	insert into EstatisticaJogador (idJogador)
	values (jogadorId)
	on conflict (idJogador) do nothing;
	
	update EstatisticaJogador
	set nrJogos = totalJogos
	where idJogador = jogadorId;

	return totalJogos;
end;
$$;

select totalJogosJogador(6); -- instruction that tests the function