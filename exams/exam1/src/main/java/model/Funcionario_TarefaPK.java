package model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Funcionario_TarefaPK implements Serializable {

    public Funcionario_TarefaPK() {
    }

    private int num_func;

    private int id_tarefa;

    public int getNumFunc() {
        return num_func;
    }

    public void setNumFunc(int num_func) {
        this.num_func = num_func;
    }

    public int getIdTarefa() {
        return id_tarefa;
    }

    public void setIdTarefa(int id_tarefa) {
        this.id_tarefa = id_tarefa;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if(!(other instanceof Funcionario_TarefaPK)) return false;

        Funcionario_TarefaPK castOther = (Funcionario_TarefaPK) other;

        return this.id_tarefa == castOther.id_tarefa && this.num_func == castOther.num_func;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num_func, id_tarefa);
    }
}
