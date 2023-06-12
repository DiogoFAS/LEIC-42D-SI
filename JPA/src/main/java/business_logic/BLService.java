package business_logic;

import annotations.Description;
import annotations.Function;
import annotations.ReturnsTable;
import dataManagement.DataScope;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jpa_routines.AssociarCracha;
import jpa_routines.AssociarCrachaBaseline;
import jpa_routines.AssociarCrachaJPA;
import routine_manager.routine.RoutineRegisters;
import table_returns.JogadorPontos;
import table_returns.JogadorTotalInfo;


import java.util.List;

import static routine_manager.routine.RoutineControllers.callRoutine;

public class BLService {

    public BLService() throws Exception {
        RoutineRegisters.registerAllRoutines();
    }

    @Description("Create new player")
    public void criarJogador(String nomeJogador, String emailJogador, String regiaoJogador) throws Exception {
        callRoutine("criarJogador", nomeJogador, emailJogador, regiaoJogador);
    }

    @Description("Deactivate player")
    public void desativarJogador(int jogadorId) throws Exception {
        callRoutine("desativarJogador", jogadorId);
    }

    @Description("Ban player")
    public void banirJogador(int jogadorId) throws Exception {
        callRoutine("banirJogador", jogadorId);
    }

    @Description("Give a badge to player")
    public void associarCracha(int jogadorId, String idJogo, String crachaNome) throws Exception {
        callRoutine("associarCracha", jogadorId, idJogo, crachaNome);
    }

    @Description("Start a conversation")
    public int iniciarConversa(int idjogador, String nomeconversa) throws Exception {
        int res = callRoutine("iniciarConversa", idjogador, nomeconversa);
        return res;
    }

    @Description("Join a player into a conversation")
    public void juntarConversa(int idJogador, int idConversa) throws Exception {
        callRoutine("juntarConversa", idJogador, idConversa);
    }

    @Description("Send a message to a chat")
    public void enviarMensagem(int jogadorId, int conversaId, String textoMensagem) throws Exception {
        callRoutine("enviarMensagem", jogadorId, conversaId, textoMensagem);
    }

    @Function
    @Description("Get the total points of a player")
    public int totalPontosJogador(int jogadorId) throws Exception {
        return callRoutine("totalPontosJogador", jogadorId);
    }

    @Function
    @Description("Retrieve the total number of different games played by a specific player")
    public int totalJogosJogador(int jogadorId) throws Exception {
        return callRoutine("totalJogosJogador", jogadorId);
    }

    @Function
    @ReturnsTable
    @Description("Retrieve a table containing the points of all players in a specific game")
    public List<JogadorPontos> PontosJogoPorJogador(String jogoNome) throws Exception {
        return callRoutine("PontosJogoPorJogador", jogoNome);
    }

    @Description("Give a badge to player (BaseLine)")
    public void associarCrachaBaseLine(int jogadorId, String idJogo, String crachaNome) throws Exception {
        AssociarCracha s = new AssociarCrachaBaseline();
        s.associarCracha(jogadorId, idJogo, crachaNome);
    }

    @Description("Give a badge to player (JPA)")
    public void associarCrachaJPA(int jogadorId, String idJogo, String crachaNome) throws Exception {
        AssociarCracha s = new AssociarCrachaJPA();
        s.associarCracha(jogadorId, idJogo, crachaNome);
    }

    @ReturnsTable
    @Description("Retrieve a view with all the information of each player")
    public List jogadorTotalInfo() throws Exception {
        try (DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();
            Query q = em.createNamedQuery("jogadorTotalInfo", JogadorTotalInfo.class);
            return q.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
