package table_returns;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
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
