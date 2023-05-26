package model;

import jakarta.persistence.*;

@Entity
@Table(name = "jogar", schema = "public")
public class Jogar {
    @EmbeddedId
    private JogarId id;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "idpartida", referencedColumnName = "id", nullable = false),
            @JoinColumn(name = "nomejogo", referencedColumnName = "nomejogo", nullable = false)
    })
    private Partida partida;

    @MapsId("idjogador")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idjogador", nullable = false)
    private Jogador idjogador;

    @Column(name = "pontuacao")
    private Integer pontuacao;

    public JogarId getId() {
        return id;
    }

    public void setId(JogarId id) {
        this.id = id;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Jogador getIdjogador() {
        return idjogador;
    }

    public void setIdjogador(Jogador idjogador) {
        this.idjogador = idjogador;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

}