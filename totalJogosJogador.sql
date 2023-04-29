--drop function totalPontosJogador(integer);

create or replace function totalJogosJogador(jogadorId int)
returns int
language plpgSQL as
$$
declare 
	totalJogos integer;
	nJogosNormais integer;
    nJogosMulti integer;
    -- c cursor for select SALARY FROM EMPLOYEE;
begin
	--begin
	--	perform * from Jogador where id = jogadorId;
	--	exception
	--		when no_data_found then
	--			raise exception 'Jogador com o id % não existe.',jogadorId;
	--end;
	
	if not exists (select * from Jogador where id = jogadorId) then
		raise exception 'Jogador com o id % não existe.',jogadorId;
	end if;
	
	select coalesce(count(DISTINCT nomeJogo),0)
	into nJogosNormais
	from Normal 
	where idJogador = jogadorId;

    select coalesce(count(DISTINCT nomeJogo), 0)
    into nJogosMulti
    from Partida
    where id = (select idPartida from Jogar where idJogador = jogadorId);
	
	totalJogos = nJogosMulti + nJogosNormais;

	return totalJogos;
end;
$$;

-- select totalJogosJogador(2); -- instruction that tests the function