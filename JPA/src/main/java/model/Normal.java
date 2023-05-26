package model;

import jakarta.persistence.*;

//@Entity
//@Table(name = "normal", schema = "public")
//public class Normal {
//    @EmbeddedId
//    private NormalId id;
//
//    @MapsId
//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumns({
//            @JoinColumn(name = "idpartida", referencedColumnName = "id", nullable = false),
//            @JoinColumn(name = "nomejogo", referencedColumnName = "nomejogo", nullable = false)
//    })
//    private Partida partida;
//
//    @Column(name = "dificuldade")
//    private Integer dificuldade;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "idjogador")
//    private Jogador idjogador;
//
//    @Column(name = "pontuacao")
//    private Integer pontuacao;
//
//    public NormalId getId() {
//        return id;
//    }
//
//    public void setId(NormalId id) {
//        this.id = id;
//    }
//
//    public Partida getPartida() {
//        return partida;
//    }
//
//    public void setPartida(Partida partida) {
//        this.partida = partida;
//    }
//
//    public Integer getDificuldade() {
//        return dificuldade;
//    }
//
//    public void setDificuldade(Integer dificuldade) {
//        this.dificuldade = dificuldade;
//    }
//
//    public Jogador getIdjogador() {
//        return idjogador;
//    }
//
//    public void setIdjogador(Jogador idjogador) {
//        this.idjogador = idjogador;
//    }
//
//    public Integer getPontuacao() {
//        return pontuacao;
//    }
//
//    public void setPontuacao(Integer pontuacao) {
//        this.pontuacao = pontuacao;
//    }
//
//}