package jpa_routines;

import dataManagement.DataScope;
import dataManagement.Mapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import model.*;
import routine_manager.function.FunctionManager;
import table_returns.JogadorPontos;

import java.util.List;
import java.util.NoSuchElementException;

public class AssociarCracha {

    // This implementation accesses stored procedure
    public static void associarCrachaJPA(Integer idJogador, String nomeJogo, String crachaNome) throws Exception {

        Mapper<Jogador, Integer> m1 = new Mapper<>(Jogador.class, Integer.class);
        Jogador j = m1.read(idJogador);
        if (j == null) throw new NoSuchElementException("Jogador não existe.");

        Mapper<Jogo, String> m2 = new Mapper<>(Jogo.class, String.class);
        Jogo jogo = m2.read(nomeJogo);
        if (jogo == null) throw new NoSuchElementException("Jogo não existe.");

        Mapper<Cracha, CrachaId> m3 = new Mapper<>(Cracha.class, CrachaId.class);
        CrachaId cId = new CrachaId();
        cId.setNome(crachaNome);
        cId.setNomejogo(jogo.getNome());
        Cracha c = m3.read(cId);
        if (c == null) throw new NoSuchElementException("Cracha não existe.");

        Integer limitePontos = c.getLimitedepontos();

        Object[] args = {nomeJogo};

        List<JogadorPontos> res = FunctionManager.executeFunction("PontosJogoPorJogador",
                JogadorPontos.class, args
        );

        int pontos = 0;

        for (JogadorPontos jp : res) {
            if (jp.idJogador == idJogador) pontos = jp.totalPontos;
        }

        if (pontos < limitePontos)
            throw new IllegalArgumentException("Jogador sem pontos suficientes.");

        TemId temPK = new TemId();
        temPK.setIdjogador(idJogador);
        temPK.setNomecracha(crachaNome);
        temPK.setNomejogo(nomeJogo);

        Tem tem = new Tem();
        tem.setId(temPK);
        tem.setIdjogador(j);
        tem.setCracha(c);

        Mapper<Tem, TemId> m4 = new Mapper<>(Tem.class, TemId.class);
        m4.create(tem);
    }

    // This implementation doesn't access any stored procedure
    public void associarCrachaBaseline(Integer idJogador, String nomeJogo, String crachaNome) throws Exception {

        Mapper<Jogador, Integer> m1 = new Mapper<>(Jogador.class, Integer.class);
        Jogador j = m1.read(idJogador);
        if (j == null) throw new NoSuchElementException("Jogador não existe.");

        Mapper<Jogo, String> m2 = new Mapper<>(Jogo.class, String.class);
        Jogo jogo = m2.read(nomeJogo);
        if (jogo == null) throw new NoSuchElementException("Jogo não existe.");

        Mapper<Cracha, CrachaId> m3 = new Mapper<>(Cracha.class, CrachaId.class);
        CrachaId cId = new CrachaId();
        cId.setNome(crachaNome);
        cId.setNomejogo(jogo.getNome());
        Cracha c = m3.read(cId);
        if (c == null) throw new NoSuchElementException("Cracha não existe.");

        Integer limitePontos = c.getLimitedepontos();

        Object[] args = {nomeJogo, idJogador};

        List<JogadorPontos> res = PontosJogoDeJogador(args);

        int pontos = 0;

        for (JogadorPontos jp : res) {
            if (jp.idJogador == idJogador) pontos = jp.totalPontos;
        }

        if (pontos < limitePontos)
            throw new IllegalArgumentException("Jogador sem pontos suficientes.");

        TemId temPK = new TemId();
        temPK.setIdjogador(idJogador);
        temPK.setNomecracha(crachaNome);
        temPK.setNomejogo(nomeJogo);

        Tem tem = new Tem();
        tem.setId(temPK);
        tem.setIdjogador(j);
        tem.setCracha(c);

        Mapper<Tem, TemId> m4 = new Mapper<>(Tem.class, TemId.class);
        m4.create(tem);
    }

    private List<JogadorPontos> PontosJogoDeJogador(Object[] args) throws Exception {
        try (DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();
            Query res = em.createNamedQuery("pontosJogoPorJogador", JogadorPontos.class)
                    .setParameter("jogoNome", args[0]);
            return res.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}


