package businessLogic;

import jakarta.persistence.*;

import utils.DataScope;
import utils.FunctionParameter;
import utils.Utils;

import static utils.Utils.arrayToArgs;


public class BLService {

    public void registerAllFunctions() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SI");
        EntityManager em = emf.createEntityManager();

        try {

        } catch (Exception e) {

        }
    }

    public void test() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SI");
        EntityManager em = emf.createEntityManager();
        try {
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
            Utils.registerFunction(functionName, argsJogador, em);

            Object[] args2 = {3}; // idJogador
            Integer totalJogos = (Integer) Utils.executeFunction("totalJogosJogador", args2);
            System.out.println("total games of player 3 is " + totalJogos);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }
}