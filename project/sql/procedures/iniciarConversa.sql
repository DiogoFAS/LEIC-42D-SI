--drop procedure iniciarConversa(int, varchar(20), out int);

create or replace procedure iniciarConversa(idJogador int, nomeConversa varchar(20), out res int)
language plpgSQL as
$$
begin
	set transaction isolation level repeatable read;

	if not exists(select * from Jogador where id = idJogador) then
		raise exception 'Jogador com o id % não existe.',idJogador;
	end if;

	insert into conversa (idJogador, nome)
	values (idJogador, nomeConversa) returning id into res;
end;
$$;