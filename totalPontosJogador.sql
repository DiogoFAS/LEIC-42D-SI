create or replace function totalPontosJogador(id int)
returns int
language plpgSQL as
$$
declare 
	totalPontos integer;
	temporario integer;
begin
	select coalesce(sum(pontuacao),0)
	into totalPontos
	from Jogar 
	where idJogador = id;
	
	select coalesce(sum(pontuacao),0)
	into temporario
	from Normal
	where idJogador = id;

	return totalPontos + temporario;
end;
$$;

select totalPontosJogador(1);