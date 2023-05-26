package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "professores")
public class Professore {
    @Id
    @Column(name = "numprof", nullable = false, precision = 5)
    private BigDecimal id;

    @Column(name = "nomeprof", length = 80)
    private String nomeprof;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNomeprof() {
        return nomeprof;
    }

    public void setNomeprof(String nomeprof) {
        this.nomeprof = nomeprof;
    }

}