create or replace procedure test_totalJogosJogador() --Jogador existente mas nenhuma partida.
language plpgSQL as
$$
declare 
	JogadorId int;
	totalJogosFunc int;
	totalEstatistica int;
begin
	insert into Regiao (nome) 
	values ('Coimbra');
	--on conflict (nome) do nothing;
	
	insert into Jogador (estado, userName, email, nomeRegiao)
	values ('Ativo', 'Rodrigo', 'rodrigo23@gmail.com', 'Coimbra')
	returning id into JogadorId;
	--on conflict (userName, email) do nothing;
	
	select totalJogosJogador(JogadorId)
	into totalJogosFunc;

	select nrJogos
	into totalEstatistica 
	from Estatistica 
	where idJogador = jogadorId;

	if totalJogosFunc = totalEstatistica and totalJogosFunc = 0 then
		raise notice 'Teste1: Obter total de jogos de jogador sem partidas: Resultado OK';
	else
		raise notice 'Teste1: Obter total de pontos de jogador sem partidas: Resultado FAIL';
	end if;
	
	rollback;
end;
$$;

call test_totalJogosJogador();