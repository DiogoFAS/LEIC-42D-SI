package table_returns;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(name = "jogadorTotalInfo", query = "select j from jogadorTotalInfo j")
public class JogadorTotalInfo {

    @Id
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "estado", nullable = false)
    public String estado;

    @Column(name = "username", nullable = false)
    public String username;

    @Column(name = "email", nullable = false)
    public String email;

    @Column(name = "totaljogosjogador", nullable = false)
    public Integer totaljogosjogador;

    @Column(name = "totalpontosjogador", nullable = false)
    public Integer totalpontosjogador;

    @Column(name = "totalpartidasjogador", nullable = false)
    public Integer totalpartidasjogador;

    public JogadorTotalInfo() {

    }
}
