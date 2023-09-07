create or replace function fun_opt_lock() returns trigger
language plpgSQL as $$
begin
	if TG_OP = 'INSERT' and new.vers is null then
		new.vers = 1;
	elseif TG_OP = 'UPDATE' then
		new.vers = old.vers + 1;
	end if;
	return new;
end;$$;

CREATE or replace TRIGGER GL_Opt
BEFORE insert or UPDATE on Cracha
FOR EACH ROW
	execute function fun_opt_lock();