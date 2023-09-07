package exam1.src.main.java.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Funcionario_Tarefa")
public class Funcionario_Tarefa {

    public Funcionario_Tarefa() {
    }

    @ManyToOne
    @MapsId("num_func")
    @JoinColumn(name = "num_func")
    private Funcionario funcionario;

    @ManyToOne
    @MapsId("id_tarefa")
    @JoinColumn(name = "id_tarefa")
    private Tarefa tarefa;

    @EmbeddedId
    private Funcionario_TarefaPK id;

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public Funcionario_TarefaPK getId() {
        return id;
    }

    public void setId(Funcionario_TarefaPK id) {
        this.id = id;
    }
}

