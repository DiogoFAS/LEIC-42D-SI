package utils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class JogadorPontos {

    @Id
    @Column(name = "idjogador", nullable = false)
    int idJogador;

    @Column(name = "totalpontos", nullable = false)
    int pontos;

    @Override
    public String toString() {
        return "Jogador: " + this.idJogador + "\npontos: " + this.pontos;
    }
}
