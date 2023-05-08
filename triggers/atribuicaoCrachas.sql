--drop

create or replace function atribuicaoCrachas() returns trigger
language plpgSQL as
$$
declare
	msg text;
	jogoId varchar(10);
	jogadorC cursor (PartidaId int) for select idJogador from Jogar where idPartida = PartidaId;
	crachaC cursor (jogoNome varchar(20)) for select nome from Cracha where nomeJogo = jogoNome;
begin

	if (TG_OP <> 'UPDATE') then -- or TG_OP <> 'INSERT'
		raise exception 'gatilho inválido';
	end if;	
	
	select id
	into jogoId
	from Jogo
	where nome = new.nomeJogo;
	
	for jogador in jogadorC(new.idPartida) loop -- percorrer todos os jogadores daquela partida.
		for nomeCracha in crachaC(new.nomeJogo) loop -- percorrer todos os crachas daquele jogo.
			begin 
				call associarCracha(jogador.idJogador, jogoId, nomeCracha.nome);
				exception
					when others then
						null;
			end;
		end loop;
	end loop;
	
	raise notice 'gatilho acionado';
	return null;
end;
$$;

create trigger atribuicaoCrachas
after update of estado on MultiJogador
for each row 
	execute procedure atribuicaoCrachas();
	
create trigger atribuicaoCrachas
after insert on Normal
for each row 
	execute procedure atribuicaoCrachas();
	
--when new.estado = 'Terminada'
select * from MultiJogador;
select * from Jogar;
select * from Tem;

update MultiJogador set estado = 'Terminada' where idPartida = 4;