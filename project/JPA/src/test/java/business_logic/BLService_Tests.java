package business_logic;

import annotations.Description;
import dataManagement.DataScope;
import dataManagement.Mapper;
import jakarta.persistence.*;
import model.Cracha;
import model.CrachaId;

import javax.xml.crypto.Data;

class GameOnAppExc extends Exception {
    public GameOnAppExc(String msg) {
        super(msg);
    }
}

public class BLService_Tests {

    @Description("Test the manages conection and transactions")
    public void teste1() throws Exception {
        // ****************** Teste 1 ****************
        // Mudar nome da base de dados
        Cracha c;
        Cracha ent;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("TetrisRank1");
            crachaKeys.setNomejogo("Tetris");
            c = em.find(Cracha.class, crachaKeys, LockModeType.OPTIMISTIC);
            if (c == null)
                throw new GameOnAppExc("cracha inexistente");

            int observedValue = c.getLimitedepontos();
            int newValue = (int) (c.getLimitedepontos() * 1.2);

            if (c.getLimitedepontos() == observedValue) {
                c.setLimitedepontos(newValue);
                em.getTransaction().commit();
            }
            em.getTransaction().commit();

        } catch (RollbackException|OptimisticLockException e) {
            if (e.getCause() instanceof OptimisticLockException || e instanceof OptimisticLockException) {
                if (e instanceof OptimisticLockException)
                    ent = (Cracha) ((OptimisticLockException) e).getEntity(); // devolve null
                else
                    ent = (Cracha) ((OptimisticLockException) e.getCause()).getEntity(); // devolve null
                System.out.printf("Erro de concorrência com o crachá: %d\n", ent.getId());
                if (em.getTransaction().isActive())
                    em.getTransaction().rollback();
            } else throw e;
        }
    }

    @Description("Test pessimistic concurrency control")
    public void teste2() throws Exception {
        // ****************** Teste 2 ****************

        // Mudar nome da base de dados
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            CrachaId crachaKeys1 = new CrachaId();
            crachaKeys1.setNome("TetrisRank1");
            crachaKeys1.setNomejogo("Tetris");

            Cracha c1 = em.find(Cracha.class, crachaKeys1, LockModeType.PESSIMISTIC_FORCE_INCREMENT);

            int observedValue = c1.getLimitedepontos();
            int newValue = (int) (c1.getLimitedepontos() * 1.2);

            if (c1.getLimitedepontos() == observedValue) {
                c1.setLimitedepontos(newValue);
                em.getTransaction().commit();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            em.getTransaction().rollback();
            throw e;
        }
    }
}