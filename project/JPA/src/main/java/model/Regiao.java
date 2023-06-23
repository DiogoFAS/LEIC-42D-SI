package model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.List;


@Entity
@Table(name = "regiao", schema = "public")
public class Regiao {
    @Id
    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "nomeregiao")
    private List<Jogador> jogadors;

    public List<Jogador> getJogadors() {
        return jogadors;
    }
//, nullable = false, length = 10
    public void setJogadors(List<Jogador> jogadors) {
        this.jogadors = jogadors;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}