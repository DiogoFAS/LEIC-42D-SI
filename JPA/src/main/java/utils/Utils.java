package utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    static Map<String, StoredProcedureQuery> functionMap = new HashMap();

    public static void clearConsole() {
        for (int y = 0; y < 25; y++) { // clears 25 lines of console window.
            System.out.println("\n");
        }
    }

    public static void executeProcedure(String procName, Object[] args, EntityManager em) throws Exception {
        em.getTransaction().begin();
        Query q = em.createNativeQuery("call " + procName + arrayToArgs(args));
        for (int i = 0; i < args.length; i++) {
            q.setParameter(i + 1, args[i]);
        }
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public static void registerFunction(String funName, FunctionParameter[] params, EntityManager em) throws Exception {
        StoredProcedureQuery f = em.createStoredProcedureQuery(funName);
        if(functionMap.get(funName) == null) {
            for (int i = 0; i < params.length; i++) {
                f.registerStoredProcedureParameter(i + 1, params[i].clazz(), params[i].mode());
            }
            functionMap.put(funName, f);
        }
    }

    public static Object executeFunction(String funName, Object... args) {
        StoredProcedureQuery q = functionMap.get(funName);
        for (int i = 0; i < args.length; i++) {
            q.setParameter(i + 1, args[i]);
        }
        q.execute();
        return q.getOutputParameterValue(args.length + 1);
    }

    public static String arrayToArgs(Object[] arr) {
        StringBuilder res = new StringBuilder("(");
        for (int i = 0; i < arr.length; i++) {
            res.append("?" + (i + 1));
            if (arr[arr.length - 1] != arr[i]) res.append(",");
        }
        res.append(")");
        return res.toString();
    }
}