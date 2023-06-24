package model;

import jakarta.persistence.*;

@Entity
@Table(name = "estatisticajogo", schema = "public")
public class Estatisticajogo {

    @Id
    @Column(name = "nomejogo", nullable = false, length = 20)
    private String nomejogo;

    @MapsId
    @OneToOne
    @JoinColumn(name = "nomejogo", nullable = false)
    private Jogo jogo;

    @Column(name = "nrpartidas")
    private Integer nrpartidas;

    @Column(name = "nrjogadores")
    private Integer nrjogadores;

    @Column(name = "totalpontos")
    private Integer totalpontos;

    public String getNomejogo() {
        return nomejogo;
    }

    public void setNomejogo(String nomejogo) {
        this.nomejogo = nomejogo;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public Integer getNrpartidas() {
        return nrpartidas;
    }

    public void setNrpartidas(Integer nrpartidas) {
        this.nrpartidas = nrpartidas;
    }

    public Integer getNrjogadores() {
        return nrjogadores;
    }

    public void setNrjogadores(Integer nrjogadores) {
        this.nrjogadores = nrjogadores;
    }

    public Integer getTotalpontos() {
        return totalpontos;
    }

    public void setTotalpontos(Integer totalpontos) {
        this.totalpontos = totalpontos;
    }

}