package exam1.src.main.java.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Funcionario")
public class Funcionario {

    public Funcionario(int num, String nome, int idade) {
        this.num = num;
        this.nome = nome;
        this.idade = idade;
    }

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.PERSIST)
    private List<Funcionario_Tarefa> fts = new ArrayList<>();

    @Id
    private int num;

    private String nome;

    private int idade;

    public Funcionario() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void addFT(Funcionario_Tarefa ft) {
        fts.add(ft);
    }
}
