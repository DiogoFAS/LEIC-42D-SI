package model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.persistence.annotations.OptimisticLocking;
import org.eclipse.persistence.annotations.OptimisticLockingType;

@Entity
@Table(name = "crachaOpt", schema = "public")
//No teste 7, retirar comentários da linha seguinte,
// Nos restantes, comentar
//@OptimisticLocking(cascade=true,type=OptimisticLockingType.CHANGED_COLUMNS)
public class CrachaOpt {
    @EmbeddedId
    private CrachaId id;

    //@MapsId("nomejogo")
    @ManyToOne//(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nomejogo",insertable=false, updatable=false)
    private Jogo nomejogo;

    @Version
    @Column(name="vers")
    private Integer vers;

    @Version
    @Column(name="oldVers")
    private Integer oldVers;

    @Column(name = "limitedepontos")
    private Integer limitedepontos;

    @Column(name = "url", length = 50)
    private String url;

    @ManyToMany
    // isto está comentado porque dá erro e não sei corrigir.
    /*@JoinTable(name = "tem",
            joinColumns = @JoinColumn(name = "crachaId", referencedColumnName = "nomecracha"),
            inverseJoinColumns = @JoinColumn(name = "idjogador", referencedColumnName = "idjogador"))*/
    private Set<Jogador> jogadors = new LinkedHashSet<>();

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

    public Integer getVers() {
        return this.vers;
    }

    public Integer setVers(int version) {
        return this.vers = version;
    }

    public Integer getOldVers() {
        return this.oldVers;
    }

    public Integer setOldVers(int oldVersion) {
        return this.oldVers = oldVersion;
    }
}
