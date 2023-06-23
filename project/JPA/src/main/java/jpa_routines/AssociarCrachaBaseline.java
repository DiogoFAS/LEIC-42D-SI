package jpa_routines;

import routine_manager.function.FunctionManager;
import table_returns.JogadorPontos;

import java.util.List;

public class AssociarCrachaBaseline extends AssociarCracha {

    // This implementation accesses stored procedure
    @Override
    public List<JogadorPontos> pontosJogoPorJogador(Object[] args) {
        return FunctionManager.executeFunction("PontosJogoPorJogador",
                JogadorPontos.class, args
        );
    }

}



