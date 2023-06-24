package model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.List;
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

    @ManyToMany
    private Set<Jogador> jogadors = new LinkedHashSet<>();

    @OneToMany
    private List<Cracha> crachas;

    @OneToOne(mappedBy = "jogo")
    private Estatisticajogo estatisticajogo;

    @OneToMany
    private List<Partida> partidas;

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

    public Set<Jogador> getJogadors () {
        return jogadors;
    }

    public void setJogadors(Set<Jogador> jogadors) {
        this.jogadors = jogadors;
    }

    public List<Cracha> getCrachas() {
        return crachas;
    }

    public void setCrachas(List<Cracha> crachas) {
        this.crachas = crachas;
    }

    public Estatisticajogo getEstatisticajogo() {
        return estatisticajogo;
    }

    public void setEstatisticajogo(Estatisticajogo estatisticajogo) {
        this.estatisticajogo = estatisticajogo;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

}