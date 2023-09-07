package routine_manager.function;


import annotations.Function;
import annotations.ReturnsTable;
import business_logic.BLServices;
import dataManagement.DataScope;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import routine_manager.routine.RoutineParameter;
import routine_manager.routine.RoutineRegisters;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class FunctionManager {

    private static final Logger logger = Logger.getLogger(FunctionManager.class.getName());

    public static<T> T executeFunction(String funName, Class<?> returnClass, Object[] args) {
        try (DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();
            StoredProcedureQuery f = em.createStoredProcedureQuery(funName, returnClass);
            RoutineParameter[] params = RoutineRegisters.getRoutineParams(funName);

            for (int i = 0; i < params.length; i++) {
                f.registerStoredProcedureParameter(i + 1, params[i].clazz(), params[i].mode());
                if (i < args.length) f.setParameter(i + 1, args[i]);
            }

            f.execute();
            if (returnsTable(funName)) {
                return (T) f.getResultList();
            }
            return (T) f.getOutputParameterValue(args.length + 1);
        } catch (Exception e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Boolean isFunction(String funName) {
        for (Method method : BLServices.class.getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.equals(funName)) return method.isAnnotationPresent(Function.class);
        }
        return false;
    }

    public static Boolean returnsTable(String funName) {
        for (Method method : BLServices.class.getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.equals(funName)) return method.isAnnotationPresent(ReturnsTable.class);
        }
        return false;
    }

    public static Class<?> getReturnType(String funName) throws NoSuchMethodException {
        for (Method method : BLServices.class.getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.equals(funName)) {
                Class<?> returnType = method.getReturnType();
                if (returnType == List.class || returnType == Arrays.class)
                    return (Class<?>) ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0];
                return returnType;
            }
        }
        throw new NoSuchMethodException("Method " + funName + "doesnt exist.");
    }
}
