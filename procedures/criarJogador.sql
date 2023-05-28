-- stored procedure criarJogador

create or replace procedure criarJogador(nomeJogador varchar(20), emailJogador varchar(30), 
										regiaoJogador varchar(10))										
language plpgSQL as
$$
begin
	set transaction isolation level read committed;
	
	if exists (select * from Jogador where userName = nomeJogador) then
		raise exception 'Já existe um jogador com o nome %.', nomeJogador;
	end if;
	
	if exists (select * from Jogador where email = emailJogador) then
		raise exception 'Já existe um jogador com o email %.',emailJogador;
	end if;
	
	if not exists (select * from Regiao where nome = regiaoJogador) then
		insert into Regiao (nome) values (regiaoJogador);
	end if;
	
	insert into Jogador (username, email, nomeRegiao) 
	values (nomeJogador, emailJogador, regiaoJogador);
end;
$$;