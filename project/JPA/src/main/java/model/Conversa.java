package model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "conversa", schema = "public")
public class Conversa {

    @EmbeddedId
    private ConversaId id;

    @ManyToOne
    @JoinColumn(name = "idjogador", insertable=false, updatable=false)
    private Jogador idjogador;

    @Column(name = "nome", length = 20)
    private String nome;

    @OneToMany(mappedBy = "conversa")
    private Set<Mensagem> mensagems = new LinkedHashSet<>();

    public ConversaId getId() {
        return id;
    }

    public void setId(ConversaId id) {
        this.id = id;
    }

    public Jogador getIdjogador() {
        return idjogador;
    }

    public void setIdjogador(Jogador idjogador) {
        this.idjogador = idjogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Mensagem> getMensagems() {
        return mensagems;
    }

    public void setMensagems(Set<Mensagem> mensagems) {
        this.mensagems = mensagems;
    }

}