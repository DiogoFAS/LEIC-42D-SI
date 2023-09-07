package model;

import jakarta.persistence.*;

@Entity
@Table(name = "jogar", schema = "public")
public class Jogar {

    @EmbeddedId
    private JogarId id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idpartida", referencedColumnName = "id", insertable=false, updatable=false),
            @JoinColumn(name = "nomejogo", referencedColumnName = "nomejogo", insertable=false, updatable=false)
    })
    private Multijogador multiJogador;

    @ManyToOne
    @JoinColumn(name = "idjogador", insertable=false, updatable=false)
    private Jogador jogador;

    @Column(name = "pontuacao")
    private Integer pontuacao;

    public JogarId getId() {
        return id;
    }

    public void setId(JogarId id) {
        this.id = id;
    }

    public Multijogador getPartida() {
        return multiJogador;
    }

    public void setPartida(Multijogador multijogador) {
        this.multiJogador = multijogador;
    }

    public Jogador getIdjogador() {
        return jogador;
    }

    public void setIdjogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

}