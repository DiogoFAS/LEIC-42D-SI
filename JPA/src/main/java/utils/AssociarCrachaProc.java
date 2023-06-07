package utils;

import dataManagement.DataScope;
import dataManagement.Mapper;
import jakarta.persistence.*;
import model.*;
import routine_manager.function.FunctionManager;

import java.util.*;

public class AssociarCrachaProc {

    public static void associarCrachaJPA(Integer idJogador, String nomeJogo, String crachaNome) throws Exception {
        //try(DataScope scope = new DataScope()) {
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

        List<Object[]> res = (List<Object[]>) FunctionManager.executeFunction("PontosJogoPorJogador", args);

        int pontos = find(res, idJogador);

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
        //    scope.validateWork();
        //} catch(Exception e) {
        //    System.out.println(e.getMessage());
        //    throw new e;
        //}
    }

    public void associarCracha(Integer idJogador, String nomeJogo, String crachaNome) throws Exception {
        //try (DataScope scope = new DataScope()) {
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

        JogadorPontos[] res = PontosJogoPorJogador(args).toArray(JogadorPontos[]::new);
        System.out.println(res);
        JogadorPontos jp = res[0];
        String jpString = jp.toString();
        System.out.println(jpString);

        //if (pontos < limitePontos)
        //    throw new IllegalArgumentException("Jogador sem pontos suficientes.");

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
        //    scope.validateWork();
        //} catch (Exception e) {
        //    System.out.println(e.getMessage());
        //    throw new RuntimeException(e);
        //}
    }

    private List<JogadorPontos> PontosJogoPorJogador(Object[] args) throws Exception {
        try (DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();
            Query res = em.createNamedQuery("pontosJogoPorJogador", JogadorPontos.class)
                    .setParameter("jogoNome", args[0]);
            List<JogadorPontos> resultList = res.getResultList();
            return resultList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static int find(List<Object[]> table, int idJogador) {
        for (Object[] x : table) {
            if ((int) x[0] == idJogador) {
                return (int) x[1];
            }
        }
        return 0;
    }
}
