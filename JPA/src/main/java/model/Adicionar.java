package model;

import jakarta.persistence.*;

@Entity
@Table(name = "adicionar", schema = "public")
public class Adicionar {
    @EmbeddedId
    private AdicionarId id;

    @MapsId("idjogador")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idjogador", nullable = false)
    private Jogador idjogador;

    @MapsId("idjogadoramigo")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idjogadoramigo", nullable = false)
    private Jogador idjogadoramigo;

    public AdicionarId getId() {
        return id;
    }

    public void setId(AdicionarId id) {
        this.id = id;
    }

    public Jogador getIdjogador() {
        return idjogador;
    }

    public void setIdjogador(Jogador idjogador) {
        this.idjogador = idjogador;
    }

    public Jogador getIdjogadoramigo() {
        return idjogadoramigo;
    }

    public void setIdjogadoramigo(Jogador idjogadoramigo) {
        this.idjogadoramigo = idjogadoramigo;
    }

}