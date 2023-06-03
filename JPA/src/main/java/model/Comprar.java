package model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "comprar", schema = "public")
public class Comprar {
    @EmbeddedId
    private ComprarId id;

    //@MapsId("idjogador")
    @ManyToOne//(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idjogador", insertable=false, updatable=false)
    private Jogador idjogador;

    //@MapsId("nomejogo")
    @ManyToOne//(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nomejogo", insertable=false, updatable=false)
    private Jogo nomejogo;

    @Column(name = "preco")
    private Integer preco;

    @Column(name = "datacompra")
    private Instant datacompra;

    public ComprarId getId() {
        return id;
    }

    public void setId(ComprarId id) {
        this.id = id;
    }

    public Jogador getIdjogador() {
        return idjogador;
    }

    public void setIdjogador(Jogador idjogador) {
        this.idjogador = idjogador;
    }

    public Jogo getNomejogo() {
        return nomejogo;
    }

    public void setNomejogo(Jogo nomejogo) {
        this.nomejogo = nomejogo;
    }

    public Integer getPreco() {
        return preco;
    }

    public void setPreco(Integer preco) {
        this.preco = preco;
    }

    public Instant getDatacompra() {
        return datacompra;
    }

    public void setDatacompra(Instant datacompra) {
        this.datacompra = datacompra;
    }

}