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
        try(DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();
            CrachaId crachaKeys = new CrachaId();
            //insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('TetrisRank1', 'Tetris', 1000, 'http:/XadrezRank1.jpg');
            crachaKeys.setNome("TetrisRank1");
            crachaKeys.setNomejogo("Tetris");

            Cracha c = em.find(Cracha.class, crachaKeys);
            if (c == null) throw new Exception("Count find cracha.");
            System.out.println("teste1 success!");
            scope.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Description("Test the manages conection and transactions")
    public void teste2() throws Exception {
        // ****************** Teste 2 ****************

        //Verificar início e fim de transações na BD
        try(DataScope scope =  new DataScope()) {
            EntityManager em = scope.getEntityManager();
            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("TetrisRank1");
            crachaKeys.setNomejogo("Tetris");
            //acquire unit of work
            //Conta a = em.find(Conta.class, Long.valueOf(1111),LockModeType.PESSIMISTIC_WRITE);

            Cracha c = em.find(Cracha.class, crachaKeys);
            c.setLimitedepontos((int) (c.getLimitedepontos() + (c.getLimitedepontos() * 0.2)));
            scope.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }


    @Description("Test the manages conection and transactions")
    public void teste3() throws Exception {
        // ****************** Teste 3 ****************

        //Verificar início e fim de transações na BD
        try(DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();

            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("TetrisRank1");
            crachaKeys.setNomejogo("Tetris");

            //acquire unit of work
            //Conta a = em.find(Conta.class, Long.valueOf(1111),LockModeType.PESSIMISTIC_WRITE);
            Cracha c = em.find(Cracha.class, crachaKeys);
            c.setLimitedepontos((int) (c.getLimitedepontos() + (c.getLimitedepontos() * 0.2)));
            em.flush();
            scope.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Description("Test concurrency control")
    public void teste4() throws Exception {
        // ****************** Teste 4 ****************
        //Verificar problemas de conssistência
        try(DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();

            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("TetrisRank1");
            crachaKeys.setNomejogo("Tetris");

            Cracha c = em.find(Cracha.class, crachaKeys);

            if (c.getLimitedepontos() >= 600) {
                c.setLimitedepontos( (c.getLimitedepontos() - 600));
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Description("Test pessimistic concurrency control")
    public void teste5() throws Exception {
        // ****************** Teste 5 ****************

        //Controlo de concorrência pessimista
         try(DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();

            CrachaId crachaKeys1 = new CrachaId();
            crachaKeys1.setNome("TetrisRank1");
            crachaKeys1.setNomejogo("Tetris");

            Cracha c1 = em.find(Cracha.class, crachaKeys1, LockModeType.PESSIMISTIC_READ);

            CrachaId crachaKeys2 = new CrachaId();
            // insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('DamasRank1', 'Damas', 1000, 'http://DamasRank1.jpg');
            crachaKeys2.setNome("SpaceInv");
            crachaKeys2.setNomejogo("SpaceInvRank1");

            Cracha c2 = em.find(Cracha.class, crachaKeys2, LockModeType.PESSIMISTIC_WRITE);

            c1.setLimitedepontos((int) (c1.getLimitedepontos() + (c1.getLimitedepontos() * 0.2)));
            em.flush();
            scope.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Description("Test optimistic concurrency control")
    public void teste6() throws Exception {
        // ****************** Teste 6 ****************

        //Controlo de concorrência otimista
        // Para ser usado com @OptimisticLocking(cascade=true,type=OptimisticLockingType.CHANGED_COLUMNS) comentado em ContaOpt.java
        //
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Mapper<Cracha, CrachaId> map = new Mapper<>(Cracha.class, CrachaId.class);

            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("TetrisRank1");
            crachaKeys.setNomejogo("Tetris");

            Cracha c = em.find(Cracha.class, crachaKeys, LockModeType.OPTIMISTIC);

            c.setLimitedepontos((int) (c.getLimitedepontos() + (c.getLimitedepontos() * 0.2)));
            map.update(c);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Description("Test optimistic concurrency control")
    public void teste7() throws Exception {
        // ****************** Teste 7 ****************

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("TetrisRank1");
            crachaKeys.setNomejogo("Tetris");

            Cracha c = em.find(Cracha.class, crachaKeys);

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

    @Description("Test optimistic concurrency control")
    public void teste8(int nreps) throws Exception {
        // ****************** Teste 8 ****************
        Cracha c = null;
        Cracha ent;

        try(DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();
            do {
                try {
                    --nreps;
                    CrachaId crachaKeys = new CrachaId();
                    crachaKeys.setNome("TetrisRank1");
                    crachaKeys.setNomejogo("Tetris");
                    c = em.find(Cracha.class, crachaKeys, LockModeType.OPTIMISTIC);
                    if (c == null)
                        throw new GameOnAppExc("cracha inexistente");

                    c.setLimitedepontos((int) (c.getLimitedepontos() + (c.getLimitedepontos() * 0.2)));
                    // experimentar com flush e com commit
                    //em.flush();

                    nreps = 0;
                    scope.validateWork();
                } catch (RollbackException | OptimisticLockException e) {
                    if (e.getCause() instanceof OptimisticLockException || e instanceof OptimisticLockException) {
                        if (em.getTransaction().isActive())
                            em.getTransaction().rollback();
                        if (nreps == 0)
                            throw new GameOnAppExc("erro de concorrência");
                    } else throw e;
                }
            } while (nreps > 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }
}