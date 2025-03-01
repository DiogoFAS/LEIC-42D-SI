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

    @ManyToOne
    @JoinColumn(name = "nomejogo", insertable=false, updatable=false)
    private Jogo nomejogo;

    @Column(name = "datainicio")
    private Instant datainicio;

    @Column(name = "datafim")
    private Instant datafim;

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

    @Override
    public String toString() {
        return "PartidaId: " + this.id + "\nPartidaNomeJogo: " + this.nomejogo;
    }

}