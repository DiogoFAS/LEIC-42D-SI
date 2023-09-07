package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class JogarId implements Serializable {

    private static final long serialVersionUID = 7932423335243811938L;

    @Column(name = "idpartida", nullable = false)
    private Integer idpartida;

    @Column(name = "nomejogo", nullable = false, length = 20)
    private String nomejogo;

    @Column(name = "idjogador", nullable = false)
    private Integer idjogador;

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
        JogarId entity = (JogarId) o;
        return Objects.equals(this.nomejogo, entity.nomejogo) &&
                Objects.equals(this.idjogador, entity.idjogador) &&
                Objects.equals(this.idpartida, entity.idpartida);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomejogo, idjogador, idpartida);
    }

}