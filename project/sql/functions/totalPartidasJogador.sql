--drop function totalPartidasJogador(integer)

create or replace function totalPartidasJogador(jogadorId int)
returns int
language plpgSQL as
$$
declare
    totalNrPartidas integer;
    nrPartidasNormais integer;
    nrPartidasMulti integer;
begin
    if not exists (select * from Jogador where id = jogadorId) then
		raise exception 'Jogador com o id % não existe.',jogadorId;
	end if;

    select coalesce(count(idJogador),0)
    into nrPartidasMulti
    from Jogar
    where idJogador = jogadorId;

    select coalesce(count(idJogador),0)
    into nrPartidasNormais
    from Normal
    where idJogador = jogadorId;

    totalNrPartidas = nrPartidasNormais + nrPartidasMulti;

    return totalNrPartidas;
end;
$$;

select totalJogosJogador(3);
select totalJogosJogador(1);
