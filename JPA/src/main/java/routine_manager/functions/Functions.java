package routine_manager.functions;

import annotations.Function;
import annotations.returnTabela;
import jakarta.persistence.ParameterMode;

import java.lang.reflect.Method;


public class Functions {

    public static FunctionParameter[] getFunctionParam(String funName) throws Exception {
        for (Method method : Functions.class.getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.equals(funName)) return (FunctionParameter[]) method.invoke(null);
        }
        throw new NoSuchMethodException("Function " + funName + " doesn't have params!");
    }

    public static Boolean isFunction(String funName) {
        for (Method method : Functions.class.getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.equals(funName)) return method.isAnnotationPresent(Function.class);
        }
        return false;
    }

    public static Boolean returnsTable(String funName) throws NoSuchMethodException {
        for (Method method : Functions.class.getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.equals(funName)) return method.isAnnotationPresent(returnTabela.class);
        }
        return false;
    }

    /**
     * 2d) criarJogador
     **/
    private static FunctionParameter[] criarJogador() {
        return new FunctionParameter[]{
                new FunctionParameter("nomeJogador", String.class, ParameterMode.IN),
                new FunctionParameter("emailJogador", String.class, ParameterMode.IN),
                new FunctionParameter("regiaoJogador", String.class, ParameterMode.IN)
        };
    }

    /**
     * 2d) desativarJogador
     **/
    private static FunctionParameter[] desativarJogador() {
        return new FunctionParameter[]{
                new FunctionParameter("idJogador", int.class, ParameterMode.IN)
        };
    }

    /**
     * 2d) banirJogador
     **/
    private static FunctionParameter[] banirJogador() {
        return new FunctionParameter[]{
                new FunctionParameter("idJogador", int.class, ParameterMode.IN)
        };
    }

    /**
     * 2e) totalPontosJogador
     **/
    @Function
    private static FunctionParameter[] totalPontosJogador() {
        return new FunctionParameter[]{
                new FunctionParameter("idJogador", int.class, ParameterMode.IN),
                new FunctionParameter("totalPontos", int.class, ParameterMode.OUT)
        };
    }

    /**
     * 2f) totalJogosJogador
     **/
    @Function
    private static FunctionParameter[] totalJogosJogador() {
        return new FunctionParameter[]{
                new FunctionParameter("idJogador", int.class, ParameterMode.IN),
                new FunctionParameter("totalJogos", int.class, ParameterMode.OUT)
        };
    }

    /**
     * 2g) pontosJogoPorJogador
     **/
    @Function
    @returnTabela
    private static FunctionParameter[] PontosJogoPorJogador() {
        return new FunctionParameter[]{
                new FunctionParameter("nomeJogo", String.class, ParameterMode.IN)
        };
    }

    /**
     * 2h) associarCracha
     **/
    private static FunctionParameter[] associarCracha() {
        return new FunctionParameter[]{
                new FunctionParameter("idJogador", int.class, ParameterMode.IN),
                new FunctionParameter("idJogo", String.class, ParameterMode.IN),
                new FunctionParameter("crachaNome", String.class, ParameterMode.IN)
        };
    }

    /**
     * 2i) iniciarConversa
     **/
    private static FunctionParameter[] iniciarConversa() {
        return new FunctionParameter[]{
                new FunctionParameter("idJogador", int.class, ParameterMode.IN),
                new FunctionParameter("nomeConversa", String.class, ParameterMode.IN),
                new FunctionParameter("res", int.class, ParameterMode.OUT)
        };
    }

    /**
     * 2j) juntarConversa
     **/
    private static FunctionParameter[] juntarConversa() {
        return new FunctionParameter[]{
                new FunctionParameter("idJogador", int.class, ParameterMode.IN),
                new FunctionParameter("idConversa", int.class, ParameterMode.IN)
        };
    }

    /**
     * 2k) enviarMensagem
     **/
    private static FunctionParameter[] enviarMensagem() {
        return new FunctionParameter[]{
                new FunctionParameter("idJogador", int.class, ParameterMode.IN),
                new FunctionParameter("idConversa", int.class, ParameterMode.IN),
                new FunctionParameter("texto", String.class, ParameterMode.IN)
        };
    }
}
