--drop view jogadorTotalInfo;
--drop function banirJogadores();

create or replace function banirJogadores() returns trigger
language plpgSQL as
$$
begin

	if (TG_OP <> 'DELETE') then
		raise exception 'gatilho inválido';
	end if;

	begin
		call banirJogador(old.id);
	end;
	
	raise notice 'gatilho acionado';
	return null;
end;
$$;

create trigger banirJogadores
INSTEAD OF delete on jogadorTotalInfo
for each row
	execute procedure banirJogadores();