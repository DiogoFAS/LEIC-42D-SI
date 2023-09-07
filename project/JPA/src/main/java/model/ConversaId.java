package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ConversaId implements Serializable {

    private static final long serialVersionUID = 962047017114476795L;

    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idjogador", nullable = false)
    private Integer idjogador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdjogador() {
        return idjogador;
    }

    public void setIdjogador(Integer idjogador) {
        this.idjogador = idjogador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversaId entity = (ConversaId) o;
        return Objects.equals(this.idjogador, entity.idjogador) &&
                Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idjogador, id);
    }

}