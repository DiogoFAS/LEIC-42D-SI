--drop atribuicaoCrachas();

create or replace function atribuicaoCrachas() returns trigger
language plpgSQL as
$$
declare
	jogoId varchar(10);
	jogador RECORD;
	cracha RECORD;
begin
	if (TG_OP <> 'UPDATE' and TG_OP <> 'INSERT') then
		raise exception 'gatilho inválido';
	end if;	
	
	select id
	into jogoId
	from Jogo
	where nome = new.nomeJogo;
	
	if (TG_TABLE_NAME = 'multijogador') then
		for jogador in select * from Jogar where idPartida = new.idPartida loop -- percorrer todos os jogadores daquela partida.
			for cracha in select * from Cracha where nomeJogo = new.nomeJogo loop-- percorrer todos os crachas daquele jogo.
				begin 
					--set transaction isolation level repeatable read;
					call associarCracha(jogador.idJogador, jogoId, cracha.nome);
					exception
						when others then
							null;
				end;
			end loop;
		end loop;
	end if;
	
	if (TG_TABLE_NAME = 'normal') then
		for jogador in select * from Normal where idPartida = new.idPartida loop -- percorrer todos os jogadores daquela partida.
			for cracha in select * from Cracha where nomeJogo = new.nomeJogo loop-- percorrer todos os crachas daquele jogo.
				begin 
					--set transaction isolation level repeatable read;
					call associarCracha(jogador.idJogador, jogoId, cracha.nome);
					exception
						when others then
							null;
				end;
			end loop;
		end loop;
	end if;
	
	return null;
end;
$$;

create trigger atribuicaoCrachas
after update of estado on MultiJogador
for each row 
when (new.estado = 'Terminada')
	execute procedure atribuicaoCrachas();
	
create trigger atribuicaoCrachas
after insert on Normal
for each row 
	execute procedure atribuicaoCrachas();