package utils;

import dataManagement.DataScope;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import model.CrachaId;
import model.CrachaOpt;

import java.util.concurrent.locks.Lock;


// This isn't being used anywhere
public class CrachaOptFunctions {
    @PrePersist
    @PreUpdate
    public void prePersistOrUpdate(CrachaOpt crachaOpt) {
        if (crachaOpt.getVers() == null) {
            crachaOpt.setVers(0);
        } else if (crachaOpt.getVers().equals(crachaOpt.getOldVers())) {
            crachaOpt.setVers(crachaOpt.getVers() + 1);
        }
    }

    public static void increaseCrachaPontos(String nomeCracha, String nomeJogo, LockModeType lock) throws Exception {
        try (DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();

            CrachaId cId = new CrachaId();
            cId.setNome(nomeCracha);
            cId.setNomejogo(nomeJogo);

            CrachaOpt c = em.find(CrachaOpt.class, cId, lock);

            c.setLimitedepontos((int) (c.getLimitedepontos() * 1.2));
            scope.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}