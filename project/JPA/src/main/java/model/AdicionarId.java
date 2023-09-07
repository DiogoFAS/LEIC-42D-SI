package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AdicionarId implements Serializable {

    private static final long serialVersionUID = 4872046109907949656L;

    @Column(name = "idjogador", nullable = false)
    private Integer idjogador;

    @Column(name = "idjogadoramigo", nullable = false)
    private Integer idjogadoramigo;

    public Integer getIdjogador() {
        return idjogador;
    }

    public void setIdjogador(Integer idjogador) {
        this.idjogador = idjogador;
    }

    public Integer getIdjogadoramigo() {
        return idjogadoramigo;
    }

    public void setIdjogadoramigo(Integer idjogadoramigo) {
        this.idjogadoramigo = idjogadoramigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdicionarId entity = (AdicionarId) o;
        return Objects.equals(this.idjogador, entity.idjogador) &&
                Objects.equals(this.idjogadoramigo, entity.idjogadoramigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idjogador, idjogadoramigo);
    }

}