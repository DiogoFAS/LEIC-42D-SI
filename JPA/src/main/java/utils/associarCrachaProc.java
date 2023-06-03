package utils;

import dataManagement.DataScope;
import dataManagement.Mapper;
import jakarta.persistence.ParameterMode;
import model.*;
import routine_manager.functions.FunctionParameter;
import routine_manager.functions.Functions;

import java.sql.ResultSet;
import java.util.NoSuchElementException;

/*public class associarCrachaProc {

    public static void associarCracha(Integer idJogador, String idJogo, String crachaNome) {
        try(DataScope scope = new DataScope()) {
            Mapper<Jogador, Integer> m1 = new Mapper<>(Jogador.class, Integer.class);
            Jogador j = m1.read(idJogador);
            if (j == null) throw new NoSuchElementException("Jogador não existe.");

            Mapper<Jogo, String> m2 = new Mapper<>(Jogo.class, String.class);
            Jogo jogo = m2.read(idJogo);
            if (jogo == null) throw new NoSuchElementException("Jogo não existe.");

            Mapper<Cracha, CrachaId> m3 = new Mapper<>(Cracha.class, CrachaId.class);
            CrachaId cId = new CrachaId();
            cId.setNome(crachaNome);
            cId.setNomejogo(jogo.getNome());
            Cracha c = m3.read(cId);
            if (c == null) throw new NoSuchElementException("Cracha não existe.");

            String nomeJogo = jogo.getNome();
            Integer limitePontos = c.getLimitedepontos();

            FunctionParameter nomeDoJogo = new FunctionParameter("nomeJogo", String.class, ParameterMode.IN);
            //FunctionParameter tabela

            FunctionParameter[] args = { nomeDoJogo };

            ResultSet res = (ResultSet) Functions.executeFunction("PontosJogoPorJogador", args, nomeJogo);

            if (res.getInt("totalPontos") < limitePontos)
                throw new IllegalArgumentException("Jogador sem pontos suficientes.");

            TemId temPK = new TemId();
            temPK.setIdjogador(idJogador);
            temPK.setNomecracha(crachaNome);
            temPK.setNomejogo(nomeJogo);

            Tem tem = new Tem();
            tem.setId(temPK);
            tem.setIdjogador(j);

            Mapper<Tem, String> m4 = new Mapper<>(Tem.class, TemId.class);
            m4.create(tem);
            scope.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}*/
