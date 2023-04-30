-- Procedimento armazenado enviarMensagem

create or replace procedure enviarMensagem(jogadorId int, conversaId int, textoConversa varchar(200))
language plpgSQL as
$$
begin
	if not exists(select * from Jogador where id = jogadorId) then
		raise exception 'Jogador com o id % não existe.',jogadorId;
	end if;
	
	if not exists(select * from Conversa where id = conversaId) then
		raise exception 'Conversa com o id % não existe.',conversaId;
	end if;
	
	if exists(select * from Conversa where id = conversaId and idJogador = jogadorId) then
		insert into Mensagem (idConversa, idJogador, texto)
		values (conversaId, jogadorId, textoConversa);
	else 
		raise exception 'Conversa com o id do jogador % não existe.',jogadorId;
	end if;
end;
$$;

do
$$
begin 
	call enviarMensagem(2, 5, 'Ola');
end;
$$;
