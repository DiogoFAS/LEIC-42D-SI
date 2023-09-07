package exam1.src.main.java.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Tarefa")
public class Tarefa {
    @OneToMany(mappedBy = "tarefa")
    private List<Funcionario_Tarefa> fts;

    @Id
    private int id;

    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
