package business_logic;

import annotations.Description;
import jakarta.persistence.*;
import model.Cracha;
import model.CrachaId;
import model.CrachaOpt;

class GameOnAppExc extends Exception {
    public GameOnAppExc(String msg) {
        super(msg);
    }
}

public class BLService_Tests {

    @Description("Test the manages conection and transactions")
    public void teste1() throws Exception {
        // ****************** Teste 1 ****************


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();

        try {
            CrachaId crachaKeys = new CrachaId();
            //insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('XadrezRank1', 'Xadrez', 1000, 'http:/XadrezRank1.jpg');
            crachaKeys.setNome("XadrezRank1");
            crachaKeys.setNomejogo("Xadrez");

            Cracha c = em.find(Cracha.class, crachaKeys);
            if (c == null) throw new Exception("Count find cracha.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            //release unit of work
            emf.close();
            //disconnect
        }
    }

    @Description("Test the manages conection and transactions")
    public void teste2() throws Exception {
        // ****************** Teste 2 ****************

        //Verificar iníocio e fim de transações na BD
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("XadrezRank1");
            crachaKeys.setNomejogo("Xadrez");
            //acquire unit of work
            //Conta a = em.find(Conta.class, Long.valueOf(1111),LockModeType.PESSIMISTIC_WRITE);


            Cracha c = em.find(Cracha.class, crachaKeys);
            c.setLimitedepontos((int) (c.getLimitedepontos() + (c.getLimitedepontos() * 0.2)));
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }


    @Description("Test the manages conection and transactions")
    public void teste3() throws Exception {
        // ****************** Teste 3 ****************

        //Verificar iníocio e fim de transações na BD
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.flush();

            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("XadrezRank1");
            crachaKeys.setNomejogo("Xadrez");

            //acquire unit of work
            //Conta a = em.find(Conta.class, Long.valueOf(1111),LockModeType.PESSIMISTIC_WRITE);
            Cracha c = em.find(Cracha.class, crachaKeys);
            c.setLimitedepontos((int) (c.getLimitedepontos() + (c.getLimitedepontos() * 0.2)));
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    @Description("Test concurrency control")
    public void teste4() throws Exception {
        // ****************** Teste 4 ****************

        //Verificar problemas de conssistência
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("XadrezRank1");
            crachaKeys.setNomejogo("Xadrez");

            Cracha c = em.find(Cracha.class, crachaKeys);

            if (c.getLimitedepontos() >= 600) {
                c.setLimitedepontos((int) (c.getLimitedepontos() - 600));
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    @Description("Test pessimistic concurrency control")
    public void teste5() throws Exception {
        // ****************** Teste 5 ****************

        //Controlo de concorrência pessimista
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            CrachaId crachaKeys1 = new CrachaId();
            crachaKeys1.setNome("XadrezRank1");
            crachaKeys1.setNomejogo("Xadrez");

            Cracha c1 = em.find(Cracha.class, crachaKeys1, LockModeType.PESSIMISTIC_READ);

            CrachaId crachaKeys2 = new CrachaId();
            // insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('DamasRank1', 'Damas', 1000, 'http://DamasRank1.jpg');
            crachaKeys2.setNome("DamasRank1");
            crachaKeys2.setNomejogo("Damas");

            Cracha c2 = em.find(Cracha.class, crachaKeys2, LockModeType.PESSIMISTIC_WRITE);

            c1.setLimitedepontos((int) (c1.getLimitedepontos() + (c1.getLimitedepontos() * 0.2)));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
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
            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("XadrezRank1");
            crachaKeys.setNomejogo("Xadrez");

            CrachaOpt c = em.find(CrachaOpt.class, crachaKeys, LockModeType.OPTIMISTIC);

            c.setLimitedepontos((int) (c.getLimitedepontos() + (c.getLimitedepontos() * 0.2)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
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
            crachaKeys.setNome("XadrezRank1");
            crachaKeys.setNomejogo("Xadrez");

            CrachaOpt c = em.find(CrachaOpt.class, crachaKeys);


            c.setLimitedepontos((int) (c.getLimitedepontos() + (c.getLimitedepontos() * 0.2)));
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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();

        CrachaOpt c = null;
        CrachaOpt ent;

        try {
            do {
                try {
                    --nreps;
                    em.getTransaction().begin();
                    CrachaId crachaKeys = new CrachaId();
                    crachaKeys.setNome("XadrezRank1");
                    crachaKeys.setNomejogo("Xadrez");
                    c = em.find(CrachaOpt.class, crachaKeys, LockModeType.OPTIMISTIC);
                    if (c == null)
                        throw new GameOnAppExc("conta inexistente");

                    c.setLimitedepontos((int) (c.getLimitedepontos() + (c.getLimitedepontos() * 0.2)));
                    // experimentar com flush e com commit
                    //em.flush();
                    em.getTransaction().commit();

                    nreps = 0;
                } catch (RollbackException | OptimisticLockException e) {
                    if (e.getCause() instanceof OptimisticLockException || e instanceof OptimisticLockException) {
                        if (em.getTransaction().isActive())
                            em.getTransaction().rollback();
                        if (nreps == 0)
                            throw new GameOnAppExc("erro de concorrência");
                    } else throw e;
                }
// 		    catch (RollbackException|OptimisticLockException e) {
//		    	if(e.getCause() instanceof OptimisticLockException || e instanceof OptimisticLockException) {
//		    		if(e instanceof OptimisticLockException) //nesta
//		    			ent =  (ContaOpt)((OptimisticLockException) e).getEntity(); // devolve null
//		    		else
//		    			ent=(ContaOpt)((OptimisticLockException) e.getCause()).getEntity(); // devolve null
//		    		System.out.printf("Erro de concorrência com a conta: %d\n",ent.getId());
//		    		if (em.getTransaction().isActive())
//		    	        em.getTransaction().rollback();
//		    	   if(nreps == 0)
//    		    	    throw new BankAppExc("erro de concorrência");
//		       }
//		      else throw e;
//		    }
            } while (nreps > 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }

    }
}