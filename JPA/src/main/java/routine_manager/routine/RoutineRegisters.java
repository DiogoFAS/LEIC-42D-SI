package routine_manager.routine;

import jakarta.persistence.ParameterMode;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

// This class is responsible for registering all Routines parameters
public class RoutineRegisters {

    public static Map<String, RoutineParameter[]> routinesParams = new HashMap<>();

    public static RoutineParameter[] getRoutineParams(String name) throws Exception {
        if (routinesParams.get(name) == null)
            throw new NoSuchMethodException("Function " + name + "doesn't have registered parameters.");
        return routinesParams.get(name);
    }

    public static void registerAllRoutines() throws Exception {
        for (Method method : RoutineRegisters.class.getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.equals("registerAllRoutines") || methodName.equals("getFunctionParams")) continue;
            System.out.println("Registering: " + methodName);
            method.invoke(null);
        }
    }

    private static void criarJogador() {
        routinesParams.put("criarJogador", new RoutineParameter[]{
                new RoutineParameter("nomeJogador", String.class, ParameterMode.IN),
                new RoutineParameter("emailJogador", String.class, ParameterMode.IN),
                new RoutineParameter("regiaoJogador", String.class, ParameterMode.IN)
        });
    }

    private static void desativarJogador() {
        routinesParams.put("desativarJogador", new RoutineParameter[]{
                new RoutineParameter("idJogador", int.class, ParameterMode.IN)
        });
    }

    private static void banirJogador() {
        routinesParams.put("banirJogador", new RoutineParameter[]{
                new RoutineParameter("idJogador", int.class, ParameterMode.IN)
        });
    }

    private static void associarCracha() {
        routinesParams.put("associarCracha", new RoutineParameter[]{
                new RoutineParameter("idJogador", int.class, ParameterMode.IN),
                new RoutineParameter("idJogo", String.class, ParameterMode.IN),
                new RoutineParameter("crachaNome", String.class, ParameterMode.IN)
        });
    }

    private static void iniciarConversa() {
        routinesParams.put("iniciarConversa", new RoutineParameter[]{
                new RoutineParameter("idJogador", int.class, ParameterMode.IN),
                new RoutineParameter("nomeConversa", String.class, ParameterMode.IN),
                new RoutineParameter("res", int.class, ParameterMode.OUT)
        });
    }

    private static void juntarConversa() {
        routinesParams.put("juntarConversa", new RoutineParameter[]{
                new RoutineParameter("idJogador", int.class, ParameterMode.IN),
                new RoutineParameter("idConversa", int.class, ParameterMode.IN)
        });
    }

    private static void enviarMensagem() {
        routinesParams.put("enviarMensagem", new RoutineParameter[]{
                new RoutineParameter("idJogador", int.class, ParameterMode.IN),
                new RoutineParameter("idConversa", int.class, ParameterMode.IN),
                new RoutineParameter("texto", String.class, ParameterMode.IN)
        });
    }

    private static void totalPontosJogador() {
        routinesParams.put("totalPontosJogador", new RoutineParameter[]{
                new RoutineParameter("idJogador", int.class, ParameterMode.IN),
                new RoutineParameter("totalPontos", int.class, ParameterMode.OUT)
        });
    }

    private static void totalJogosJogador() {
        routinesParams.put("totalJogosJogador", new RoutineParameter[]{
                new RoutineParameter("idJogador", int.class, ParameterMode.IN),
                new RoutineParameter("totalJogos", int.class, ParameterMode.OUT)
        });
    }

    private static void PontosJogoPorJogador() {
        routinesParams.put("PontosJogoPorJogador", new RoutineParameter[]{
                new RoutineParameter("nomeJogo", String.class, ParameterMode.IN)
        });
    }
}
