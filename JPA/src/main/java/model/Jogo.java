package model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "jogo", schema = "public")
public class Jogo {
    @Id
    @Column(name = "nome", nullable = false, length = 20)
    private String nome;

    @Column(name = "id", length = 10)
    private String id;

    @Column(name = "url", length = 50)
    private String url;

    @OneToMany(mappedBy = "jogo")
    private Set<Comprar> comprars = new LinkedHashSet<>();

    @OneToMany(mappedBy = "jogo")
    private Set<Cracha> crachas = new LinkedHashSet<>();

    @OneToOne(mappedBy = "jogo")
    private Estatisticajogo estatisticajogo;

    @OneToMany(mappedBy = "jogo")
    private Set<Partida> partidas = new LinkedHashSet<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Comprar> getComprars() {
        return comprars;
    }

    public void setComprars(Set<Comprar> comprars) {
        this.comprars = comprars;
    }

    public Set<Cracha> getCrachas() {
        return crachas;
    }

    public void setCrachas(Set<Cracha> crachas) {
        this.crachas = crachas;
    }

    public Estatisticajogo getEstatisticajogo() {
        return estatisticajogo;
    }

    public void setEstatisticajogo(Estatisticajogo estatisticajogo) {
        this.estatisticajogo = estatisticajogo;
    }

    public Set<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(Set<Partida> partidas) {
        this.partidas = partidas;
    }

}