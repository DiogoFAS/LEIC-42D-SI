package businessLogic;

import annotations.Description;
import annotations.Function;
import annotations.ReturnsTable;
import routine_manager.routine.RoutineRegisters;


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
        // TODO: return from procedure, will have to make a SQL function using alternative 2
        int res = (int) callRoutine("iniciarConversa", idjogador, nomeconversa);
        System.out.println("my res is " + res);
        return res;
    }

    @Description("Join a player into a conversation.")
    public void juntarConversa(int idJogador, int idConversa) throws Exception {
        callRoutine("juntarConversa", idJogador, idConversa);
    }

    @Description("Send a message")
    public void enviarMensagem(int jogadorId, int conversaId, String textoMensagem) throws Exception {
        callRoutine("enviarMensagem", jogadorId, conversaId, textoMensagem);
    }

    @Function
    public void totalPontosJogador() {
    }

    @Function
    public void totalJogosJogador() {
    }

    @Function
    @ReturnsTable
    public void PontosJogoPorJogador() {
    }
}
