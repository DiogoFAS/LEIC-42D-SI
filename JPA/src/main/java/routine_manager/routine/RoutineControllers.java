package routine_manager.routine;

import annotations.Description;
import annotations.ReturnsTable;
import business_logic.BLService;
import routine_manager.procedure.ProcedureManager;
import routine_manager.function.FunctionManager;
import table_returns.JogadorPontos;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import static utils.Utils.parseObject;
import static utils.Utils.printTable;

public class RoutineControllers {

    public Map<Integer, FunctionController> controllers = new HashMap<>();
    BLService srv;

    public RoutineControllers(BLService srv) {
        this.srv = srv;
        registerControllers();
    }

    public void registerControllers() {
        Arrays.stream(srv.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Description.class))
                .sorted(Comparator.comparing(method -> method.getName()))
                .forEach(method -> {
                    controllers.put(controllers.size() + 1,
                            new FunctionController(method, method.getAnnotation(Description.class).value())
                    );
                });
    }

    public void printOptions() {
        for (Map.Entry<Integer, FunctionController> c : controllers.entrySet()) {
            System.out.println(c.getKey() + " -> " + c.getValue().description);
        }
        System.out.println("exit -> Close the app\n");
    }

    public static<T> T callRoutine(String funName, Object... args) throws Exception {
        if (FunctionManager.isFunction(funName)) {
            System.out.println("Function");
            Class<?> returnType = FunctionManager.getReturnType(funName);
            return FunctionManager.executeFunction(funName, returnType, args);
        } else {
            System.out.println("Procedure");
            return ProcedureManager.executeProcedure(funName, args);
        }
    }

    public void chooseRoutine(int option) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Method method = controllers.get(option).method;
        Parameter[] params = method.getParameters();

        String name = method.getName();
        System.out.println("You selected " + name);
        Object[] args = new Object[params.length];

        for (int i = 0; i < params.length; i++) {
            System.out.println(params[i].getName() + ":");
            args[i] = parseObject(params[i].getType(), scanner.nextLine());
        }

        Object result = method.invoke(srv, args);
        Type returnType = method.getGenericReturnType();
        if (returnType == void.class) return;
        if(method.isAnnotationPresent(ReturnsTable.class)) {
            Class<?> tableClass = (Class<?>) ((ParameterizedType) returnType).getActualTypeArguments()[0];
            printTable((List<JogadorPontos>) result, tableClass);
        }
        else System.out.println(name + ": " + result);
    }

    private record FunctionController(Method method, String description) {
    }
}
