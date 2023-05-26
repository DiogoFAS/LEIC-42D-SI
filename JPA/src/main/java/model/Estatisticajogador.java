package model;

import jakarta.persistence.*;

@Entity
@Table(name = "estatisticajogador", schema = "public")
public class Estatisticajogador {
    @Id
    @Column(name = "idjogador", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idjogador", nullable = false)
    private Jogador jogador;

    @Column(name = "nrpartidas")
    private Integer nrpartidas;

    @Column(name = "nrjogos")
    private Integer nrjogos;

    @Column(name = "totalpontosjogos")
    private Integer totalpontosjogos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Integer getNrpartidas() {
        return nrpartidas;
    }

    public void setNrpartidas(Integer nrpartidas) {
        this.nrpartidas = nrpartidas;
    }

    public Integer getNrjogos() {
        return nrjogos;
    }

    public void setNrjogos(Integer nrjogos) {
        this.nrjogos = nrjogos;
    }

    public Integer getTotalpontosjogos() {
        return totalpontosjogos;
    }

    public void setTotalpontosjogos(Integer totalpontosjogos) {
        this.totalpontosjogos = totalpontosjogos;
    }

}