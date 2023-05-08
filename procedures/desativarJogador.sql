-- função desativarJogador

create or replace procedure desativarJogador(jogadorId int)
										
language plpgSQL as
$$
begin
	set transaction isolation level repeatable read;

	if not exists (select * from Jogador where id = jogadorId) then
		raise exception 'Não existe um jogador com o id %.', jogadorId;
	end if;
	
	if exists (select * from Jogador where estado = 'Inativo' and id = jogadorId) then
		raise exception 'O jogador com id % já está desativado.', jogadorId;
	end if;
	
	update Jogador
	set estado = 'Inativo'
	where id = jogadorId;
end;
$$

call desativarJogador(1);