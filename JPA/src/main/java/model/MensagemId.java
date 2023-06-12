package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MensagemId implements Serializable {

    private static final long serialVersionUID = -171149935804992878L;

    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idconversa", nullable = false)
    private Integer idconversa;

    @Column(name = "idjogador", nullable = false)
    private Integer idjogador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdconversa() {
        return idconversa;
    }

    public void setIdconversa(Integer idconversa) {
        this.idconversa = idconversa;
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
        MensagemId entity = (MensagemId) o;
        return Objects.equals(this.idjogador, entity.idjogador) &&
                Objects.equals(this.id, entity.id) &&
                Objects.equals(this.idconversa, entity.idconversa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idjogador, id, idconversa);
    }

}