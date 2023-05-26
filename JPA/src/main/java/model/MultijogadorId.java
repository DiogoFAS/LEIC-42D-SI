package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MultijogadorId implements Serializable {
    private static final long serialVersionUID = 2127478480021349954L;
    @Column(name = "idpartida", nullable = false)
    private Integer idpartida;

    @Column(name = "nomejogo", nullable = false, length = 20)
    private String nomejogo;

    public Integer getIdpartida() {
        return idpartida;
    }

    public void setIdpartida(Integer idpartida) {
        this.idpartida = idpartida;
    }

    public String getNomejogo() {
        return nomejogo;
    }

    public void setNomejogo(String nomejogo) {
        this.nomejogo = nomejogo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultijogadorId entity = (MultijogadorId) o;
        return Objects.equals(this.nomejogo, entity.nomejogo) &&
                Objects.equals(this.idpartida, entity.idpartida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomejogo, idpartida);
    }

}