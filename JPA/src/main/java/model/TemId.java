package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TemId implements Serializable {
    private static final long serialVersionUID = -2998472726943497717L;
    @Column(name = "idjogador", nullable = false)
    private Integer idjogador;

    @Column(name = "nomecracha", nullable = false, length = 20)
    private String nomecracha;

    @Column(name = "nomejogo", nullable = false, length = 20)
    private String nomejogo;

    public Integer getIdjogador() {
        return idjogador;
    }

    public void setIdjogador(Integer idjogador) {
        this.idjogador = idjogador;
    }

    public String getNomecracha() {
        return nomecracha;
    }

    public void setNomecracha(String nomecracha) {
        this.nomecracha = nomecracha;
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
        TemId entity = (TemId) o;
        return Objects.equals(this.nomejogo, entity.nomejogo) &&
                Objects.equals(this.idjogador, entity.idjogador) &&
                Objects.equals(this.nomecracha, entity.nomecracha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomejogo, idjogador, nomecracha);
    }

}