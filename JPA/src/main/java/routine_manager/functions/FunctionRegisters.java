package routine_manager.functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;

import java.lang.reflect.Method;


public class FunctionRegisters {


    public static void registerAllFunctions(EntityManager em) throws Exception {
        for (Method method : FunctionRegisters.class.getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.equals("registerAllFunctions")) continue;
            System.out.println("Registering: " + methodName);
            method.invoke(null, em);
        }
    }

    /** 2e) totalPontosJogador **/
    private static void totalPontosJogador(EntityManager em) throws Exception {
        FunctionParameter idJogador = new FunctionParameter("idJogador", Integer.class, ParameterMode.IN);
        FunctionParameter totalPontos =  new FunctionParameter("totalPontos", Integer.class, ParameterMode.OUT);

        FunctionParameter[] args = {idJogador, totalPontos};
        FunctionManager.registerFunction("totalPontosJogador", args, em);
    }

    /** 2f) totalJogosJogador **/
    private static void totalJogosJogador(EntityManager em) throws Exception {
        FunctionParameter idJogador = new FunctionParameter("idJogador", Integer.class, ParameterMode.IN);
        FunctionParameter totalJogos = new FunctionParameter("totalJogos", Integer.class, ParameterMode.OUT);

        FunctionParameter[] args = {idJogador, totalJogos};
        FunctionManager.registerFunction("totalJogosJogador", args, em);
    }

    /** 2g) PontosJogoPorJogador **/
    private static void PontosJogoPorJogador(EntityManager em) throws Exception {
        FunctionParameter nomeJogo = new FunctionParameter("nomeJogo", String.class, ParameterMode.IN);
        /* this function returns a table */
        FunctionParameter pontosPorJogador = new FunctionParameter("pontosPorJogador", Integer.class, ParameterMode.OUT);

        FunctionParameter[] args = {nomeJogo, pontosPorJogador};
        FunctionManager.registerFunction("PontosJogoPorJogador", args, em);
    }

}
