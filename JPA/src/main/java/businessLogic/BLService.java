/*
 Walter Vieira (2022-04-22)
 Sistemas de Informação - projeto JPAAulas_ex1
 Código desenvolvido para iulustração dos conceitos sobre acesso a dados, concretizados com base na especificação JPA.
 Todos os exemplos foram desenvolvidos com EclipseLinlk (3.1.0-M1), usando o ambientre Eclipse IDE versão 2022-03 (4.23.0).
 
Não existe a pretensão de que o código estaja completo.

Embora tenha sido colocado um esforço significativo na correção do código, não há garantias de que ele não contenha erros que possam 
acarretar problemas vários, em particular, no que respeita à consistência dos dados.  
 
*/

package businessLogic;

import java.util.List;
import jakarta.persistence.*;

import model.*;

/**
 * Hello world!
 *
 */

public class BLService 
{
    @SuppressWarnings("unchecked")
	public void test() throws Exception
    { //
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAExemplo");
        EntityManager em = emf.createEntityManager();
        try 
        {
            
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
/*
 Aluno al = new Aluno();
            al.setNumal(111L);
            al.setNomeal("Diogo");

            Aluno.IMapper<Aluno, Long> map = new Aluno.AlunoDataMapperImpl(); // static

            //map.Create(al,em);
            al.setNomeal("André");
            map.Update(al, em);
            Aluno aluno = map.Read(al.getNumal(), em);
            System.out.println(aluno);

            //map.Delete(al, em);

//            em.flush(); //Send changes to database
            em.clear();
 */

/*
 //Criar um aluno
            System.out.println("Criar um aluno");
            em.getTransaction().begin();

            Aluno a = new Aluno();

            a.setNumal(555);
            a.setNomeal("rui silva");
            em.persist(a);
            em.getTransaction().commit();


            //ler um aluno
            //String sql = "SELECT a.numal FROM Aluno a";
            //Query query = em.createQuery(sql);

            //List<Long> la1 = query.getResultList();
            //for (long ax : la1)
            //{
            //    System.out.printf("%d ", ax);
            //}

            String sql = "SELECT a FROM Aluno a";
            Query query = em.createQuery(sql);

            List<Aluno> la = query.getResultList();

            for (Aluno ax : la)
            {
                System.out.printf("%d ", ax.getNumal());
                System.out.printf("%s \n", ax.getNomeal());
            }


            // Criar CACIFO
            System.out.println("Criar um Cacifo");
            em.getTransaction().begin();

            Cacifo c = new Cacifo();

            c.setDescrcac("Primeiro Cacifo");
            em.persist(c);
            em.getTransaction().commit();

            System.out.printf("Numero de cacifo %d\n", c.getNumcac());


            // Criar Hobby
            System.out.println("Criar um Hobby");
            em.getTransaction().begin();

            HobbyPK pk = new HobbyPK();

            pk.setNumal(555);
            pk.setNumhb(1);

            Hobby h = new Hobby();

            h.setDescr("hobby 1");
            h.setId(pk);
            em.persist(h);
            em.getTransaction().commit();


            //DELETE
            em.getTransaction().begin();
            em.remove(em.find(Aluno.class,555L));
            em.flush(); //Send changes to database
            em.getTransaction().commit();
            em.clear();
            System.out.println("\n--# Removed!!");

 */