package model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "multijogador", schema = "public")
public class Multijogador {
    @EmbeddedId
    private MultijogadorId id;

    //@MapsId
    @OneToOne//(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "idpartida", referencedColumnName = "id", insertable=false, updatable=false),
            @JoinColumn(name = "nomejogo", referencedColumnName = "nomejogo", insertable=false, updatable=false)
    })
    private Partida partida;

    @Column(name = "estado", length = 30)
    private String estado;

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomeregiao")
    private Regiao nomeregiao;

    @ManyToMany
    @JoinColumn(name = "jogador")
    private Set<Jogador> jogadors = new HashSet<>();


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

    public Set<Jogador> getJogadors() {
        return jogadors;
    }

    public void setJogadors(Set<Jogador> jogadors) {
        this.jogadors = jogadors;
    }
}