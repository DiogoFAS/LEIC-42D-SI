--drop view jogadorTotalInfo
--drop function banirJogadores()

create or replace function banirJogadores() returns trigger
language plpgSQL as
$$
begin

	if (TG_OP <> 'DELETE') then
		raise exception 'gatilho inválido';
	end if;

	begin 
		call banirJogador(old.id);
		exception
		when others then
			null;
	end;
	
	raise notice 'gatilho acionado';
	return null;
end;
$$;

create trigger banirJogadores
INSTEAD OF delete on jogadorTotalInfo
for each row
	execute procedure banirJogadores();

delete from jogadorTotalInfo where id = 6;