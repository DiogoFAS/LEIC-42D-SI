--drop procedure juntarConversa(int, int);

create or replace procedure juntarConversa(idJogador int, idConversa int)
language plpgSQL as
$$
declare 
	nomeCon varchar(20);
begin
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
		values (idConversa, idJogador, nomeCon);
	end if;
end;
$$;

call juntarConversa(1, 6);