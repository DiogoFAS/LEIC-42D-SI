package model;

import jakarta.persistence.*;

@Entity
@Table(name = "adicionar", schema = "public")
public class Adicionar {

    @EmbeddedId
    private AdicionarId id;

    @ManyToOne
    @JoinColumn(name = "idjogador", insertable=false, updatable=false)
    private Jogador idjogador;

    @ManyToOne
    @JoinColumn(name = "idjogadoramigo", insertable=false, updatable=false)
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