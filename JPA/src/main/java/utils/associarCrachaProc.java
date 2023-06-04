package utils;

import dataManagement.Mapper;
import model.*;
import routine_manager.functions.FunctionsAux;

import java.util.List;
import java.util.NoSuchElementException;

public class associarCrachaProc {

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

        List<Object[]> res = (List<Object[]>) FunctionsAux.executeFunction("PontosJogoPorJogador", args);

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

    public static void associarCracha(Integer idJogador, String nomeJogo, String crachaNome) throws Exception {
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

        List<Object[]> res = PontosJogoPorJogador(args);

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
        //} catch (Exception e) {
        //    System.out.println(e.getMessage());
        //    throw new RuntimeException(e);
        //}
    }

    private static List<Object[]> PontosJogoPorJogador(Object[] args) {
        Mapper<Normal, NormalId> Normal = new Mapper<>(Normal.class, NormalId.class);
        //Normal.read()
        //TODO("not yet implemented")
        return null;
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
