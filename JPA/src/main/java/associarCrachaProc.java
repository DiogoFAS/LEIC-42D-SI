import dataManagement.Mapper;
import model.Cracha;
import model.Jogador;
import model.Jogo;
import routine_manager.functions.FunctionManager;

import java.sql.ResultSet;
import java.util.NoSuchElementException;

public class associarCrachaProc {

    public static void associarCracha(Integer idJogador, String idJogo, String crachaNome) {
        try {
            Mapper<Jogador, Integer> m1 = new Mapper<>(Jogador.class, Integer.class);
            Jogador j = m1.read(idJogador);
            if (j == null) throw new NoSuchElementException("Jogador não existe.");

            Mapper<Cracha, String> m2 = new Mapper<>(Cracha.class, String.class);
            Cracha c = m2.read(crachaNome);
            if (c == null) throw new NoSuchElementException("Cracha não existe.");

            Mapper<Jogo, String> m3 = new Mapper<>(Jogo.class, String.class);
            Jogo jogo = m3.read(idJogo);
            if (jogo == null) throw new NoSuchElementException("Jogo não existe.");

            String nomeJogo = jogo.getNome();
            Integer limitePontos = c.getLimitedepontos();

            ResultSet res = (ResultSet) FunctionManager.executeFunction("PontosJogoPorJogador", nomeJogo);

            if (res.getInt("totalPontos") < limitePontos)
                throw new IllegalArgumentException("Jogador sem pontos suficientes.");

            

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
