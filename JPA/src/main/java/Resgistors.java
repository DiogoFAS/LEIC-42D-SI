import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import utils.FunctionParameter;
import utils.Utils;

import java.lang.reflect.Method;

public class Resgistors {

    EntityManager em;

    public Resgistors(EntityManager em) {
        this.em = em;
    }

    public void registerAllFunctions() throws Exception {
        for (Method method : this.getClass().getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.equals("registerAllFunctions")) continue;
            System.out.println("Registering: " + methodName);
            method.invoke(null);
        }
    }

    public void totalJogosJogador() throws Exception {
        FunctionParameter idJogador = new FunctionParameter(Integer.class, ParameterMode.IN);
        FunctionParameter totalJogosPar = new FunctionParameter(Integer.class, ParameterMode.OUT);

        FunctionParameter[] args = {idJogador, totalJogosPar};
        Utils.registerFunction("totalJogosJogador", args, em);
    }

    public void PontosJogoPorJogador() throws Exception {
        // TODO
        FunctionParameter idJogador = new FunctionParameter(Integer.class, ParameterMode.IN);
        FunctionParameter totalJogosPar = new FunctionParameter(Integer.class, ParameterMode.OUT);

        FunctionParameter[] args = {idJogador, totalJogosPar};
        Utils.registerFunction("PontosJogoPorJogador", args, em);
    }

}
