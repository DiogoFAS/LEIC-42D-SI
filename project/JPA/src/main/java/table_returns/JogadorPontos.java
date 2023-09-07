package table_returns;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(name = "pontosJogoPorJogador", query =
        "select new table_returns.JogadorPontos (n.jogador.id, n.pontuacao) " +
                "from Normal n where ?1 = n.id.nomejogo " +
                "union all " +
                "select j.jogador.id, j.pontuacao from Jogar j " +
                "inner join Partida p where ?1 = p.id.nomejogo")
public class JogadorPontos {

    public JogadorPontos(Integer idJogador, Integer totalPontos) {
        this.idJogador = idJogador;
        this.totalPontos = totalPontos;
    }

    @Id
    @Column(name = "idjogador", nullable = false)
    public Integer idJogador;

    @Column(name = "totalpontos", nullable = false)
    public Integer totalPontos;

    public JogadorPontos() {
    }

    @Override
    public String toString() {
        return "Jogador: " + this.idJogador + "\npontos: " + this.totalPontos;
    }
}
