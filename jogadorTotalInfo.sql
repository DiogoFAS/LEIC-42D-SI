-- View
create view jogadorTotalInfo as
select Jogador.id, Jogador.estado, 
	Jogador.userName, Jogador.email,
	Estatistica.nrPartidas, Estatistica.nrJogos,
	Estatistica.totalPontosJogos
from Jogador, Estatistica
where Jogador.id = Estatistica.idJogador and Jogador.estado != 'Banido'

select * from jogadorTotalInfo