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

    private static void totalJogosJogador(EntityManager em) throws Exception {
        FunctionParameter idJogador = new FunctionParameter(Integer.class, ParameterMode.IN);
        FunctionParameter totalJogosPar = new FunctionParameter(Integer.class, ParameterMode.OUT);

        FunctionParameter[] args = {idJogador, totalJogosPar};
        FunctionManager.registerFunction("totalJogosJogador", args, em);
    }

    private static void PontosJogoPorJogador() throws Exception {
        // TODO
    }

}
