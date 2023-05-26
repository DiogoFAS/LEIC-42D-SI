package model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "jogador", schema = "public")
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "estado", length = 10)
    private String estado;

    @Column(name = "username", length = 20)
    private String username;

    @Column(name = "email", length = 30)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomeregiao")
    private Regiao nomeregiao;

    @ManyToMany(mappedBy = "idjogador")
    private Set<Jogador> jogadors = new LinkedHashSet<>();

    @OneToMany(mappedBy = "jogador")
    private Set<Comprar> comprars = new LinkedHashSet<>();

    @OneToMany(mappedBy = "jogador")
    private Set<Conversa> conversas = new LinkedHashSet<>();

    @OneToOne(mappedBy = "jogador")
    private Estatisticajogador estatisticajogador;

    @OneToMany(mappedBy = "jogador")
    private Set<Jogar> jogars = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idjogador")
    private Set<Normal> normals = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "idjogador")
    private Set<Cracha> crachas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Regiao getNomeregiao() {
        return nomeregiao;
    }

    public void setNomeregiao(Regiao nomeregiao) {
        this.nomeregiao = nomeregiao;
    }

    public Set<Jogador> getJogadors() {
        return jogadors;
    }

    public void setJogadors(Set<Jogador> jogadors) {
        this.jogadors = jogadors;
    }

    public Set<Comprar> getComprars() {
        return comprars;
    }

    public void setComprars(Set<Comprar> comprars) {
        this.comprars = comprars;
    }

    public Set<Conversa> getConversas() {
        return conversas;
    }

    public void setConversas(Set<Conversa> conversas) {
        this.conversas = conversas;
    }

    public Estatisticajogador getEstatisticajogador() {
        return estatisticajogador;
    }

    public void setEstatisticajogador(Estatisticajogador estatisticajogador) {
        this.estatisticajogador = estatisticajogador;
    }

    public Set<Jogar> getJogars() {
        return jogars;
    }

    public void setJogars(Set<Jogar> jogars) {
        this.jogars = jogars;
    }

    public Set<Normal> getNormals() {
        return normals;
    }

    public void setNormals(Set<Normal> normals) {
        this.normals = normals;
    }

    public Set<Cracha> getCrachas() {
        return crachas;
    }

    public void setCrachas(Set<Cracha> crachas) {
        this.crachas = crachas;
    }

}