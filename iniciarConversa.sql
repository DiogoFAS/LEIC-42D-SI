--drop procedure iniciarConversa(int, varchar(20), out int);

create or replace procedure iniciarConversaLogic(idJogador int, nomeConversa varchar(20), out res int)
language plpgSQL as
$$
begin
	set transaction isolation level read uncommitted;

	if not exists(select * from Jogador where id = idJogador) then
		raise exception 'Jogador com o id % não existe.',idJogador;
	end if;

	insert into conversa (idJogador, nome)
	values (idJogador, nomeConversa) returning id into res;
end;
$$;

create or replace procedure iniciarConversaTrans(idJogador int, nomeConversa varchar(20), out res int)
language plpgSQL as 
$$
declare 
	msg text;
begin 
	call iniciarConversaLogic(idJogador, nomeConversa, res);
	exception
		when others then
			get stacked diagnostics msg = MESSAGE_TEXT;
			raise exception '%',msg;
			rollback;
end;
$$;

create or replace procedure iniciarConversa(idJogador int, nomeConversa varchar(20), out res int)
language plpgSQL as
$$
declare
	msg text;
begin 
	commit;
		set transaction isolation level read uncommitted;
		call iniciarConversaTrans(idJogador, nomeConversa, res);
end;
$$;

do
$$
declare 
	idConversa int;
begin 
	call iniciarConversa(0, 'Chat1', idConversa);
	raise notice 'Conversa criada com o id %', idConversa;
end;
$$;