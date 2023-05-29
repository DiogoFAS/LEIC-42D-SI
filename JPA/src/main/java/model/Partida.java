package model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "partida", schema = "public")
public class Partida {
    @EmbeddedId
    private PartidaId id;

    @MapsId("nomejogo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nomejogo", nullable = false)
    private Jogo nomejogo;

    @Column(name = "datainicio")
    private Instant datainicio;

    @Column(name = "datafim")
    private Instant datafim;

    @OneToMany(mappedBy = "partida")
    private Set<Jogar> jogars = new LinkedHashSet<>();

    @OneToOne(mappedBy = "partida")
    private Multijogador multijogador;

    @OneToOne(mappedBy = "partida")
    private Normal normal;

    public PartidaId getId() {
        return id;
    }

    public void setId(PartidaId id) {
        this.id = id;
    }

    public Jogo getNomejogo() {
        return nomejogo;
    }

    public void setNomejogo(Jogo nomejogo) {
        this.nomejogo = nomejogo;
    }

    public Instant getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Instant datainicio) {
        this.datainicio = datainicio;
    }

    public Instant getDatafim() {
        return datafim;
    }

    public void setDatafim(Instant datafim) {
        this.datafim = datafim;
    }

    public Set<Jogar> getJogars() {
        return jogars;
    }

    public void setJogars(Set<Jogar> jogars) {
        this.jogars = jogars;
    }

    public Multijogador getMultijogador() {
        return multijogador;
    }

    public void setMultijogador(Multijogador multijogador) {
        this.multijogador = multijogador;
    }

    public Normal getNormal() {
        return normal;
    }

    public void setNormal(Normal normal) {
        this.normal = normal;
    }

}