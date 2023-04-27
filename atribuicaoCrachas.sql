--drop

create or replace function atribuicaoCrachas() returns trigger
language plpgSQL as
$$
begin
	if (TG_OP <> 'update') then
		raise exception 'gatilho inválido';
	end if;
	
	insert into Tem (idJogador, nomeCracha, nomeJogo)







create trigger atribuicaoCrachas
after update on MultiJogador