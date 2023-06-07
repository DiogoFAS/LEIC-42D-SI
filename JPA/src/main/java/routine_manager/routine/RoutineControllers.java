package routine_manager.routine;

import annotations.Description;
import businessLogic.BLService;
import routine_manager.procedure.ProcedureManager;
import routine_manager.function.FunctionManager;
import utils.Utils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RoutineControllers {

    public Map<Integer, FunctionController> controllers = new HashMap<>();
    BLService srv;

    public RoutineControllers(BLService srv) {
        this.srv = srv;
        registerControllers();
    }

    public void registerControllers() {
        Arrays.stream(srv.getClass().getDeclaredMethods()).forEach(method -> {
            if (!method.isAnnotationPresent(Description.class)) return;
            controllers.put(controllers.size() + 1,
                    new FunctionController(method, method.getAnnotation(Description.class).value())
            );
        });
    }

    public void printOptions() {
        for (Map.Entry<Integer, FunctionController> c : controllers.entrySet()) {
            System.out.println(c.getKey() + " -> " + c.getValue().description);
        }
        System.out.println();
    }

    public static Object callRoutine(String funName, Object... args) throws Exception {
        // Call Routine
        if (FunctionManager.isFunction(funName)) {
            System.out.println("Function");
            return FunctionManager.executeFunction(funName, args);
        } else {
            System.out.println("Procedure");
            ProcedureManager.executeProcedure(funName, args);
            return null;
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
            args[i] = Utils.parseObject(params[i].getType(), scanner.nextLine());
        }

        Object result = method.invoke(srv, args);
        if (method.getReturnType() == void.class) return;
        System.out.println(name + ": " + result);
    }

    private record FunctionController(Method method, String description) {
    }
}
