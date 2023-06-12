package table_returns;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(name = "pontosJogoPorJogador", query = "select n.jogador.id, n.pontuacao from Normal n where :jogoNome = n.id.nomejogo union all select j.jogador.id, j.pontuacao from Jogar j inner join Partida p where :jogoNome = p.id.nomejogo" )
public class JogadorPontos {

    @Id
    @Column(name = "idjogador", nullable = false)
    public Integer idJogador;

    @Column(name = "totalpontos", nullable = false)
    public Integer totalPontos;

    @Override
    public String toString() {
        return "Jogador: " + this.idJogador + "\npontos: " + this.totalPontos;
    }
}
