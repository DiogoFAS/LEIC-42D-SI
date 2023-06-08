package routine_manager.function;


import annotations.Function;
import annotations.ReturnsTable;
import business_logic.BLService;
import dataManagement.DataScope;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import routine_manager.routine.RoutineParameter;
import routine_manager.routine.RoutineRegisters;

import java.lang.reflect.Method;

public class FunctionManager {


    public static Object executeFunction(String funName, Object[] args) {
        try (DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();
            StoredProcedureQuery f = em.createStoredProcedureQuery(funName);
            RoutineParameter[] params = RoutineRegisters.getFunctionParams(funName);
            for (int i = 0; i < params.length; i++) {
                f.registerStoredProcedureParameter(i + 1, params[i].clazz(), params[i].mode());
                if(i < args.length) f.setParameter(i + 1, args[i]);
            }

            f.execute();
            if (returnsTable(funName)) {
                return f.getResultList();
            }
            return f.getOutputParameterValue(args.length + 1);
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Boolean isFunction(String funName) {
        for (Method method : BLService.class.getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.equals(funName)) return method.isAnnotationPresent(Function.class);
        }
        return false;
    }

    private static Boolean returnsTable(String funName) throws NoSuchMethodException {
        for (Method method : BLService.class.getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.equals(funName)) return method.isAnnotationPresent(ReturnsTable.class);
        }
        return false;
    }
}
