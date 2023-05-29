package model;

import jakarta.persistence.*;

@Entity
@Table(name = "multijogador", schema = "public")
public class Multijogador {
    @EmbeddedId
    private MultijogadorId id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "idpartida", referencedColumnName = "id", nullable = false),
            @JoinColumn(name = "nomejogo", referencedColumnName = "nomejogo", nullable = false)
    })
    private Partida partida;

    @Column(name = "estado", length = 30)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomeregiao")
    private Regiao nomeregiao;

    public MultijogadorId getId() {
        return id;
    }

    public void setId(MultijogadorId id) {
        this.id = id;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Regiao getNomeregiao() {
        return nomeregiao;
    }

    public void setNomeregiao(Regiao nomeregiao) {
        this.nomeregiao = nomeregiao;
    }

}