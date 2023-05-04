-- função banirJogador

create or replace procedure banirJogador(jogadorId int)
										
language plpgSQL as
$$
begin
	if not exists (select * from Jogador where id = jogadorId) then
		raise exception 'Não existe um jogador com o id %.', jogadorId;
	end if;
	
	if exists (select * from Jogador where estado = 'Banido' and id = jogadorId) then
		raise exception 'O jogador com id % já está banido.', jogadorId;
	end if;
	
	update Jogador
	set estado = 'Banido'
	where id = jogadorId;
end;
$$;

call banirJogador(2);