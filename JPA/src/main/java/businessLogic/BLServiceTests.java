package businessLogic;

import annotations.Description;
import jakarta.persistence.*;
import model.Cracha;
import model.CrachaId;
import model.CrachaOpt;

class BankAppExc extends Exception {
    public BankAppExc(String msg) { super(msg); }
}

public class BLServiceTests {

    // Increasing 20% number of points associated with a rank,
    // which it's data are the rank name and it's game ID, using optimistic locking

    /*insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('XadrezRank1', 'Xadrez', 1000, 'http:/XadrezRank1.jpg');
    insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('XadrezRank2', 'Xadrez', 500, 'http:/XadrezRank2.jpg');
    insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('XadrezRank3', 'Xadrez', 250, 'http:/XadrezRank3.jpg');
    insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('XadrezRank4', 'Xadrez', 100, 'http:/XadrezRank4.jpg');

    insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('DamasRank1', 'Damas', 1000, 'http://DamasRank1.jpg');
    insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('DamasRank2', 'Damas', 500, 'http://DamasRank2.jpg');
    insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('DamasRank3', 'Damas', 250, 'http://DamasRank3.jpg');
    insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('DamasRank4', 'Damas', 100, 'http://DamasRank4.jpg');

    insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('SnakeRank1', 'Snake', 1000, 'http://SnakeRank1.jpg');
    insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('SnakeRank2', 'Snake', 500, 'http://SnakeRank2.jpg');
    insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('SnakeRank3', 'Snake', 250, 'http://SnakeRank3.jpg');
    insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('SnakeRank4', 'Snake', 100, 'http://SnakeRank4.jpg');

    insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('BatalhaNavalRank1', 'BatalhaNaval', 1000, 'http://BatalhaNavalRank1.jpg');
    insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('BatalhaNavalRank2', 'BatalhaNaval', 500, 'http://BatalhaNavalRank2.jpg');
    insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('BatalhaNavalRank3', 'BatalhaNaval', 250, 'http://BatalhaNavalRank3.jpg');
    insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('BatalhaNavalRank4', 'BatalhaNaval', 100, 'http://BatalhaNavalRank4.jpg');*/

    @Description("Test the manages conection and transactions")
    public  void teste1() throws Exception
    {
        // ****************** Teste 1 ****************


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();

        try
        {
            CrachaId crachaKeys = new CrachaId();
            //insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('XadrezRank1', 'Xadrez', 1000, 'http:/XadrezRank1.jpg');
            crachaKeys.setNome("XadrezRank1");
            crachaKeys.setNomejogo("Xadrez");

            Cracha c = em.find(Cracha.class, crachaKeys);
            //acquire unit of work
            //Connection acquired from connection pool
            // SELECT ID, SALDO FROM contas WHERE (ID = ?)	bind => [1111]
            //Connection released to connection pool

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {

            em.close();
            //release unit of work
            emf.close();
            //disconnect
        }
    }

    @Description("Test the manages conection and transactions")
    public  void teste2() throws Exception
    {
        // ****************** Teste 2 ****************

        //Verificar iníocio e fim de transações na BD
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();
        //Connected: jdbc:postgresql://localhost:5432/SisIn (preencher pool)
        //Connection acquired from connection pool [default].
        //Connection released to connection pool [default].
        try
        {

            em.getTransaction().begin();
            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("XadrezRank1");
            crachaKeys.setNomejogo("Xadrez");
            //acquire unit of work
            //Conta a = em.find(Conta.class, Long.valueOf(1111),LockModeType.PESSIMISTIC_WRITE);


            Cracha c = em.find(Cracha.class, crachaKeys);
            //Connection acquired from connection pool
            // SELECT ID, SALDO FROM contas WHERE (ID = ?)	bind => [1111]
            //Connection released to connection pool
            c.setLimitedepontos((int) (c.getLimitedepontos() + (c.getLimitedepontos() * 0.2)));
            em.getTransaction().commit();
            //begin unit of work commit
            //Connection acquired from connection pool
            //begin transaction
            //UPDATE contas SET SALDO = ? WHERE (ID = ?)  bind => [1100.0, 1111]
            //commit transaction
            //Connection released to connection pool
            // end unit of work commit

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }


    @Description("Test the manages conection and transactions")
    public  void teste3() throws Exception
    {
        // ****************** Teste 3 ****************

        //Verificar iníocio e fim de transações na BD
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();
        //Connected: jdbc:postgresql://localhost:5432/SisIn (preencher pool)
        //Connection acquired from connection pool [default].
        //Connection released to connection pool [default].
        try
        {
            em.getTransaction().begin();
            em.flush();

            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("XadrezRank1");
            crachaKeys.setNomejogo("Xadrez");

            //acquire unit of work
            //Conta a = em.find(Conta.class, Long.valueOf(1111),LockModeType.PESSIMISTIC_WRITE);
            Cracha c = em.find(Cracha.class, crachaKeys);
            //Connection acquired from connection pool
            // SELECT ID, SALDO FROM contas WHERE (ID = ?)	bind => [1111]
            //Connection released to connection pool
            c.setLimitedepontos((int) (c.getLimitedepontos() + (c.getLimitedepontos() * 0.2)));
            em.flush();
            //begin unit of work flush
            //Connection acquired from connection pool
            //begin transaction
            //UPDATE contas SET SALDO = ? WHERE (ID = ?)  bind => [1100.0, 1111]
            //Connection released to connection pool
            // end unit of work flush
//   	 Conta c1 = em.find(Conta.class, Long.valueOf(2222));
            em.getTransaction().commit();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
    }

    @Description("Test concurrency control")
    public  void teste4() throws Exception
    {
        // ****************** Teste 4 ****************

        //Verificar problemas de conssistência
        // Na bd iniciar o saldo da conta 1111 com 1000€
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();

            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("XadrezRank1");
            crachaKeys.setNomejogo("Xadrez");

            Cracha c = em.find(Cracha.class, crachaKeys);

            // No pgadmin4 executar update contas set saldo = saldo - 600 where id = 1111 and saldo >= 600

            if (c.getLimitedepontos() >= 600) {
                c.setLimitedepontos((int) (c.getLimitedepontos() - 600));
                em.getTransaction().commit();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
        // verificxar saldo da conta 1111 ma BD
    }

    @Description("Test pessimistic concurrency control")
    public  void teste5() throws Exception
    {
        // ****************** Teste 5 ****************

        //Controlo de concorrência pessimista
        // Na bd iuniciar o saldo da conta 1111 com 1000€
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();

            CrachaId crachaKeys1 = new CrachaId();
            crachaKeys1.setNome("XadrezRank1");
            crachaKeys1.setNomejogo("Xadrez");

            Cracha c1 = em.find(Cracha.class, crachaKeys1, LockModeType.PESSIMISTIC_READ);
//      Connection acquired from connection pool
//      begin transaction
//      SELECT ID, SALDO FROM contas WHERE (ID = ?) FOR UPDATE
//  	bind => [1111]

            CrachaId crachaKeys2 = new CrachaId();
            // insert into Cracha (nome, nomeJogo, limiteDePontos, URL) values ('DamasRank1', 'Damas', 1000, 'http://DamasRank1.jpg');
            crachaKeys2.setNome("DamasRank1");
            crachaKeys2.setNomejogo("Damas");

            Cracha c2 = em.find(Cracha.class, crachaKeys2, LockModeType.PESSIMISTIC_WRITE);

            // No pgadmin4 executar update contas set saldo = saldo - 600 where id = 1111 and saldo >= 600

            /*if (c.getSaldo() >= 400) {
                c.setSaldo(c.getSaldo()-400);
                em.getTransaction().commit();
            }*/

            c1.setLimitedepontos((int) (c1.getLimitedepontos() + (c1.getLimitedepontos() * 0.2)));

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
        // verificxar saldo da conta 1111 ma BD
    }

    @Description("Test optimistic concurrency control")
    public  void teste6() throws Exception
    {
        // ****************** Teste 6 ****************

        //Controlo de concorrência otimista
        // Para ser usado com @OptimisticLocking(cascade=true,type=OptimisticLockingType.CHANGED_COLUMNS) comentado em ContaOpt.java
        //
        // Na bd iuniciar o saldo da conta 1111 com 1000€
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();

 /*Para testar o valor inicial colocada pelo JPA
ContaOpt cx = new ContaOpt();
cx.setId(9999);
cx.setSaldo(90009);
em.persist(cx);*/
            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("XadrezRank1");
            crachaKeys.setNomejogo("Xadrez");

            CrachaOpt c = em.find(CrachaOpt.class, crachaKeys, LockModeType.OPTIMISTIC);
//      Connection acquired from connection pool
//      begin transaction
//      SELECT ID, SALDO, vers FROM contasOpt WHERE (ID = ?)
//  	bind => [1111]
//      Connection released to connection pool


            // No pgadmin4 executar update contasOpt set saldo = saldo - 600 where id = 1111 and saldo >= 600

            c.setLimitedepontos((int) (c.getLimitedepontos() + (c.getLimitedepontos() * 0.2)));
                //begin transaction
                //UPDATE contasOpt SET SALDO = ?, vers = ? WHERE ((ID = ?) AND (vers = ?)) bind =>[600.0, 1, 1111, 0]
                //org.eclipse.persistence.exceptions.OptimisticLockException
                //cannot be updated because it has changed or been deleted since it was last read.
                //Class> model.ContaOpt Primary Key> 1 111
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
        // verificxar saldo da conta 1111 ma BD
    }

    @Description("Test optimistic concurrency control")
    public  void teste7() throws Exception
    {
        // ****************** Teste 7 ****************

        //Controlo de concorrência otimista
        // Para ser usado com @OptimisticLocking(cascade=true,type=OptimisticLockingType.CHANGED_COLUMNS) descomentado em ContaOpt.java
        //
        // Na bd iuniciar o saldo da conta 1111 com 1000€
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();

            CrachaId crachaKeys = new CrachaId();
            crachaKeys.setNome("XadrezRank1");
            crachaKeys.setNomejogo("Xadrez");

            CrachaOpt c = em.find(CrachaOpt.class, crachaKeys);

//      Connection acquired from connection pool
//      begin transaction
//      SELECT ID, SALDO, vers FROM contasOpt WHERE (ID = ?)
//  	bind => [1111]
//      Connection released to connection pool


            // No pgadmin4 executar update contas set saldo = saldo - 600 where id = 1111 and saldo >= 600

            c.setLimitedepontos((int) (c.getLimitedepontos() + (c.getLimitedepontos() * 0.2)));
                //begin transaction
                //UPDATE contasOpt SET SALDO = ?, vers = ? WHERE ((ID = ?) AND (vers = ?)) bind =>[600.0, 1, 1111, 0]
                //org.eclipse.persistence.exceptions.OptimisticLockException
                //cannot be updated because it has changed or been deleted since it was last read.
                //Class> model.ContaOpt Primary Key> 1 111
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }
        // verificxar saldo da conta 1111 ma BD
    }

    @Description("Test optimistic concurrency control")
    public  void teste8(int nreps) throws Exception
    {
        // ****************** Teste 8 ****************

        //Controlo de concorrência otimista - Reconciliação
        // para ser usado com @OptimisticLocking(cascade=true,type=OptimisticLockingType.CHANGED_COLUMNS) comentado
        // em ContaOpt.java
        // Na bd iuniciar o saldo da conta 1111 com 1000€
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();

        CrachaOpt c = null;
        CrachaOpt ent;

        try
        {

            do {
                try
                {
                    --nreps;
                    em.getTransaction().begin();
                    CrachaId crachaKeys = new CrachaId();
                    crachaKeys.setNome("XadrezRank1");
                    crachaKeys.setNomejogo("Xadrez");
                    c = em.find(CrachaOpt.class, crachaKeys, LockModeType.OPTIMISTIC);
                    if(c==null)
                        throw new BankAppExc("conta inexistente");

                    c.setLimitedepontos((int) (c.getLimitedepontos() + (c.getLimitedepontos() * 0.2)));
                        // experimentar com flush e com commit
                        //em.flush();
                        em.getTransaction().commit();

                    nreps = 0;
                }

                catch (RollbackException|OptimisticLockException e) {
                    if(e.getCause() instanceof OptimisticLockException || e instanceof OptimisticLockException) {
                        if (em.getTransaction().isActive())
                            em.getTransaction().rollback();
                        if(nreps == 0)
                            throw new BankAppExc("erro de concorrência");
                    }
                    else throw e;
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
            } while(nreps > 0);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            em.close();
            emf.close();
        }

    }
}
