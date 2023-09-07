package jpa_routines;

import routine_manager.query.QueryManager;
import table_returns.JogadorPontos;

import java.util.List;

public class AssociarCrachaJPA extends AssociarCracha {

    // This implementation doesn't access any stored procedure
    @Override
    public List<JogadorPontos> pontosJogoPorJogador(Object[] args) throws Exception {
        return QueryManager.executeNamedQuery("pontosJogoPorJogador", JogadorPontos.class, args);
    }
}
