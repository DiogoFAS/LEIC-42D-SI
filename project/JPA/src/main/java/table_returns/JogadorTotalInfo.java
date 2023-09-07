package table_returns;

import jakarta.persistence.*;

@Entity
@NamedQuery(name = "jogadorTotalInfo", query =
        "select new table_returns.JogadorTotalInfo(j.id, j.estado, j.username, j.email, j.totaljogosjogador, j.totalpontosjogador, j.totalpartidasjogador) " +
                "from JogadorTotalInfo j")
public class JogadorTotalInfo {

    public JogadorTotalInfo(
            Integer id,
            String estado,
            String username,
            String email,
            Integer totaljogosjogador,
            Integer totalpontosjogador,
            Integer totalpartidasjogador
    ) {
        this.id = id;
        this.estado = estado;
        this.username = username;
        this.email = email;
        this.totaljogosjogador = totaljogosjogador;
        this.totalpontosjogador = totalpontosjogador;
        this.totalpartidasjogador = totalpartidasjogador;
    }

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
