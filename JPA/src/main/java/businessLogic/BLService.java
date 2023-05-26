/*
 Walter Vieira (2022-04-22)
 Sistemas de Informa��o - projeto JPAAulas_ex1
 C�digo desenvolvido para iulustra��o dos conceitos sobre acesso a dados, concretizados com base na especifica��o JPA.
 Todos os exemplos foram desenvolvidos com EclipseLinlk (3.1.0-M1), usando o ambientre Eclipse IDE vers�o 2022-03 (4.23.0).
 
N�o existe a pretens�o de que o c�digo estaja completo.

Embora tenha sido colocado um esfor�o significativo na corre��o do c�digo, n�o h� garantias de que ele n�o contenha erros que possam 
acarretar problemas v�rios, em particular, no que respeita � consist�ncia dos dados.  
 
*/

package businessLogic;

import jakarta.persistence.*;

import utils.DataScope;
import utils.FunctionParameter;
import utils.Utils;

import static utils.Utils.arrayToArgs;

/**
 * Hello world!
 *
 */

public class BLService 
{
    @SuppressWarnings("unchecked")
	public void test() throws Exception
    { //
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("SI");
        EntityManager em = emf.createEntityManager();
        try 
        {
            /*System.out.println("criarJogador procedure");

            String procedureName = "criarJogador";

            String nome = "Diogo";
            String email = "Diogo@gmail.com";
            String regiao = "Sintra";

            Object[] args1 = { nome, email, regiao};

            Utils.executeProcedure(procedureName, args1, em);*/


            System.out.println("totalJogosJogador function");

            String functionName = "totalJogosJogador";

            FunctionParameter idJogador = new FunctionParameter(Integer.class, ParameterMode.IN);
            FunctionParameter totalJogosPar = new FunctionParameter(Integer.class, ParameterMode.OUT);

            FunctionParameter[] argsJogador = {idJogador, totalJogosPar};

            Object[] args2 = { 3 }; // idJogador

            System.out.println("totalJogosJogador function registered!");
            Utils.registerFunction(functionName, argsJogador, em);

            System.out.println("totalJogosJogador function executed!");
            Integer totalJogos = (Integer) Utils.executeFunction(functionName, args2);
            System.out.println(totalJogos);
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