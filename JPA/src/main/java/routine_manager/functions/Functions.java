package routine_manager.functions;


import dataManagement.DataScope;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

import java.util.Map;

import static routine_manager.functions.FunctionParameter.parametersMap;

public class Functions {
    public static Object executeFunction(String funName, Object[] args) {
        try (DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();
            StoredProcedureQuery f = em.createStoredProcedureQuery(funName);
            FunctionParameter[] params = parametersMap.get(funName);
            for (int i = 0; i < params.length; i++) {
                f.registerStoredProcedureParameter(i + 1, params[i].clazz(), params[i].mode());
            }
            for (int i = 0; i < args.length; i++) {
                f.setParameter(i + 1, args[i]);
            }
            f.execute();
            return f.getOutputParameterValue(args.length);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
