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

    //@OneToMany//(mappedBy = "jogo")
    //private List<Comprar> comprars; //= new LinkedHashSet<>(); // deveria ser list?

    @ManyToMany
    private Set<Jogador> jogadors = new LinkedHashSet<>();

    @OneToMany//(mappedBy = "jogo")
    private List<Cracha> crachas;// = new LinkedHashSet<>();

    //@OneToOne(mappedBy = "jogo")
    //private Estatisticajogo estatisticajogo;

    @OneToMany//(mappedBy = "jogo")
    private List<Partida> partidas;// = new LinkedHashSet<>();

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

    /*public List<Comprar> getComprars() {
        return comprars;
    }*/

    public Set<Jogador> getJogadors () {
        return jogadors;
    }

    /*public void setComprars(List<Comprar> comprars) {
        this.comprars = comprars;
    }*/

    public void setJogadors(Set<Jogador> jogadors) {
        this.jogadors = jogadors;
    }

    public List<Cracha> getCrachas() {
        return crachas;
    }

    public void setCrachas(List<Cracha> crachas) {
        this.crachas = crachas;
    }

    /*public Estatisticajogo getEstatisticajogo() {
        return estatisticajogo;
    }

    public void setEstatisticajogo(Estatisticajogo estatisticajogo) {
        this.estatisticajogo = estatisticajogo;
    }*/

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

}