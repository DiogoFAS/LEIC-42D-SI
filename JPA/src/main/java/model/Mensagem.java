package model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "mensagem", schema = "public")
public class Mensagem {
    @EmbeddedId
    private MensagemId id;

    //@MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "idconversa", referencedColumnName = "id", insertable=false, updatable=false),
            @JoinColumn(name = "idjogador", referencedColumnName = "idjogador", insertable=false, updatable=false)
    })
    private Conversa conversa;

    @Column(name = "texto", length = 200)
    private String texto;

    @Column(name = "datadeenvio")
    private Instant datadeenvio;

    public MensagemId getId() {
        return id;
    }

    public void setId(MensagemId id) {
        this.id = id;
    }

    public Conversa getConversa() {
        return conversa;
    }

    public void setConversa(Conversa conversa) {
        this.conversa = conversa;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Instant getDatadeenvio() {
        return datadeenvio;
    }

    public void setDatadeenvio(Instant datadeenvio) {
        this.datadeenvio = datadeenvio;
    }

}