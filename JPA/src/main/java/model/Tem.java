package model;

import jakarta.persistence.*;

@Entity
@Table(name = "tem")
public class Tem {
    @EmbeddedId
    private TemId id;

    //@MapsId("idjogador")
    @ManyToOne//(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idjogador", insertable=false, updatable=false)
    private Jogador idjogador;

    //@MapsId
    @ManyToOne//(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "nomecracha", referencedColumnName = "nome", insertable=false, updatable=false),
            @JoinColumn(name = "nomejogo", referencedColumnName = "nomejogo", insertable=false, updatable=false)
    })
    private Cracha cracha;

    public TemId getId() {
        return id;
    }

    public void setId(TemId id) {
        this.id = id;
    }

    public Jogador getIdjogador() {
        return idjogador;
    }

    public void setIdjogador(Jogador idjogador) {
        this.idjogador = idjogador;
    }

    public Cracha getCracha() {
        return cracha;
    }

    public void setCracha(Cracha cracha) {
        this.cracha = cracha;
    }

}