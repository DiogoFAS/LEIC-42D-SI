package model;

import jakarta.persistence.*;
import org.eclipse.persistence.annotations.OptimisticLocking;
import org.eclipse.persistence.annotations.OptimisticLockingType;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "cracha", schema = "public")
@OptimisticLocking(type= OptimisticLockingType.CHANGED_COLUMNS)
public class Cracha {

    @EmbeddedId
    private CrachaId id;

    @ManyToOne
    @JoinColumn(name = "nomejogo",insertable=false, updatable=false)
    private Jogo nomejogo;

    @Column(name = "limitedepontos")
    private Integer limitedepontos;

    @Column(name = "url", length = 50)
    private String url;

    @ManyToMany
    @JoinColumns({
            @JoinColumn(name = "crachaId", referencedColumnName = "nomecracha", insertable=false, updatable=false),
            @JoinColumn(name = "idJogador", referencedColumnName = "idJogador", insertable=false, updatable=false)
    })
    private Set<Jogador> jogadors = new LinkedHashSet<>();

    @Column(name = "vers")
    private Integer vers;

    public Integer getVers() {
        return vers;
    }

    public void setVers(Integer vers) {
        this.vers = vers;
    }

    public CrachaId getId() {
        return id;
    }

    public void setId(CrachaId id) {
        this.id = id;
    }

    public Jogo getNomejogo() {
        return nomejogo;
    }

    public void setNomejogo(Jogo nomejogo) {
        this.nomejogo = nomejogo;
    }

    public Integer getLimitedepontos() {
        return limitedepontos;
    }

    public void setLimitedepontos(Integer limitedepontos) {
        this.limitedepontos = limitedepontos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Jogador> getJogadors() {
        return jogadors;
    }

    public void setJogadors(Set<Jogador> jogadors) {
        this.jogadors = jogadors;
    }

}