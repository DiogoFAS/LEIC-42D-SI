package jpa_routines;

import dataManagement.Mapper;
import jakarta.persistence.RollbackException;
import model.*;
import table_returns.JogadorPontos;

import java.util.logging.Logger;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class AssociarCracha {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public void associarCracha(Integer idJogador, String nomeJogo, String crachaNome) throws Exception {

        try {
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

            List<JogadorPontos> res = pontosJogoPorJogador(args);

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
        } catch (RollbackException e) {
            logger.severe(e.getLocalizedMessage());
        }
    }

    abstract List<JogadorPontos> pontosJogoPorJogador(Object[] args) throws Exception;
}


