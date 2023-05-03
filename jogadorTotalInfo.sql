-- View
create view jogadorTotalInfo as
select Jogador.id, Jogador.estado, 
	Jogador.userName, Jogador.email,
	totalJogosJogador(Jogador.id),
	totalPontosJogador(Jogador.id)
	--Estatistica.nrPartidas
	--O NÚMERO DE PARTIDAS DO JOGADOR NÃO É POSSÍVEL OBTER, POIS NÃO EXISTE UMA FORMA DE CÁLCULA-LAS
from Jogador
where Jogador.estado != 'Banido'
