--drop procedure juntarConversa(int, int);

create or replace procedure juntarConversaLogic(idJogador int, idConversa int)
language plpgSQL as
$$
declare 
	nomeCon varchar(20);
begin
	set transaction isolation level repeatable read;
	
	if not exists(select * from Jogador where id = idJogador) then
		raise exception 'Jogador com o id % não existe.',idJogador;
	end if;
	
	select nome
	into nomeCon
	from Conversa
	where id = idConversa;

	if nomeCon is null then
		raise exception 'Conversa com o id % não existe.',idConversa;
	else 
		insert into Conversa (id, idJogador, nome)
		values (idConversa, idJogador, nomeCon)
		on conflict (id, idJogador) do nothing;
	end if;
end;
$$;

create or replace procedure juntarConversaTrans(idJogador int, idConversa int)
language plpgSQL as 
$$
declare 
	msg text;
begin 
	call juntarConversaLogic(idJogador, nomeConversa, res);
	exception
		when others then
			get stacked diagnostics msg = MESSAGE_TEXT;
			raise exception '%',msg;
			rollback;
end;
$$;

create or replace procedure juntarConversa(idJogador int, idConversa int)
language plpgSQL as
$$
declare
	msg text;
begin 
	commit;
		set transaction isolation level read uncommitted;
		call juntarConversaTrans(idJogador, nomeConversa, res);
end;
$$;

call juntarConversa(3, 1);