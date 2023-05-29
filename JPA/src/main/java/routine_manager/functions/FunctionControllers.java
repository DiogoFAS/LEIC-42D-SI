package routine_manager.functions;

import annotations.Description;
import businessLogic.BLService;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FunctionControllers {

    BLService srv;
    Integer i = 1;
    public Map<Integer, FunctionController> controllers = new HashMap<>();

    public FunctionControllers(BLService srv) {
        this.srv = srv;
        registerControllers();
    }

    public void registerControllers() {
        Arrays.stream(BLService.class.getDeclaredMethods()).forEach(it ->
                controllers.put(i++, new FunctionController(it, it.getAnnotation(Description.class).value()))
        );
    }

    public void printOptions() {
        for (Map.Entry<Integer, FunctionController> c : controllers.entrySet()) {
            System.out.println(c.getKey() + " -> " + c.getValue().description);
        }
        System.out.println();
    }

    private class FunctionController {
        Method method;
        String description;

        public FunctionController(Method method, String description) {
            this.method = method;
            this.description = description;
        }
    }
}
