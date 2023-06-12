package business_logic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Cracha;
import model.CrachaId;

public class run implements Runnable {
    @Override
    public void run() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SI");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("TetrisRank1");
            crachaKeys.setNomejogo("Tetris");

            Cracha c = em.find(Cracha.class, crachaKeys);

            c.setLimitedepontos((int) (c.getLimitedepontos() * 1.2));
            System.out.println("Thread committed!");
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
