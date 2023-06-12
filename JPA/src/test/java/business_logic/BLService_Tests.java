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

    @Description("Test optimistic concurrency control")
    public void teste1() throws Exception {
        // ****************** Teste 1 ****************

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SI");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("TetrisRank1");
            crachaKeys.setNomejogo("Tetris");

            Cracha c = em.find(Cracha.class, crachaKeys, LockModeType.OPTIMISTIC);

            Runnable run = new run();
            Thread t1 =new Thread(run);

            c.setLimitedepontos((int) (c.getLimitedepontos() * 1.2));
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

}