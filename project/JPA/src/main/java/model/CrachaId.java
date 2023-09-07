package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CrachaId implements Serializable {

    private static final long serialVersionUID = -4666862235538601739L;

    @Column(name = "nome", nullable = false, length = 20)
    private String nome;

    @Column(name = "nomejogo", nullable = false, length = 20)
    private String nomejogo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        CrachaId entity = (CrachaId) o;
        return Objects.equals(this.nomejogo, entity.nomejogo) &&
                Objects.equals(this.nome, entity.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomejogo, nome);
    }

}