package model;

import jakarta.persistence.*;

@Entity
@Table(name = "normal", schema = "public")
public class Normal {

    @EmbeddedId
    private NormalId id;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "idpartida", referencedColumnName = "id", insertable=false, updatable=false),
            @JoinColumn(name = "nomejogo", referencedColumnName = "nomejogo", insertable=false, updatable=false)
    })
    private Partida partida;

    @Column(name = "dificuldade")
    private Integer dificuldade;

    @ManyToOne
    @JoinColumn(name = "idjogador")
    private Jogador jogador;

    @Column(name = "pontuacao")
    private Integer pontuacao;

    public NormalId getId() {
        return id;
    }

    public void setId(NormalId id) {
        this.id = id;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Integer getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Integer dificuldade) {
        this.dificuldade = dificuldade;
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