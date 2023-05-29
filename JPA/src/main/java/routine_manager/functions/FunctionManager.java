package routine_manager.functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;

import java.util.HashMap;
import java.util.Map;

public class FunctionManager {

    static Map<String, StoredProcedureQuery> functions = new HashMap<>();

    public static void registerFunction(String funName, FunctionParameter[] params, EntityManager em) throws Exception {
        StoredProcedureQuery f = em.createStoredProcedureQuery(funName);
        if (functions.get(funName) == null) {
            for (int i = 0; i < params.length; i++) {
                f.registerStoredProcedureParameter(i + 1, params[i].clazz(), params[i].mode());
            }
            functions.put(funName, f);
        }
    }

    public static Object executeFunction(String funName, Object... args) {
        StoredProcedureQuery q = functions.get(funName);
        for (int i = 0; i < args.length; i++) {
            q.setParameter(i + 1, args[i]);
        }
        q.execute();
        return q.getOutputParameterValue(args.length + 1);
    }
}
