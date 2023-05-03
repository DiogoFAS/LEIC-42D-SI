--drop view if exists jogadorTotalInfo;

-- View
create view jogadorTotalInfo as
select Jogador.id, Jogador.estado, 
	Jogador.userName, Jogador.email,
	totalJogosJogador(Jogador.id),
	totalPontosJogador(Jogador.id),
	totalPartidasJogador(Jogador.id)
from Jogador
where Jogador.estado != 'Banido'

select * from jogadorTotalInfo;

select * from jogadorTotalInfo