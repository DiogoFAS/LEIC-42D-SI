package routine_manager;

import dataManagement.DataScope;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;

import java.util.HashMap;
import java.util.Map;

public class ProcedureManager {

    public static void executeProcedure(String procName, Object[] args) throws Exception {
        try (DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();
            Query q = em.createNativeQuery("call " + procName + arrayToArgs(args));
            for (int i = 0; i < args.length; i++) {
                q.setParameter(i + 1, args[i]);
            }
            q.executeUpdate();
            scope.validateWork();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    private static String arrayToArgs(Object[] arr) {
        StringBuilder res = new StringBuilder("(");
        for (int i = 0; i < arr.length; i++) {
            res.append("?" + (i + 1));
            if (arr[arr.length - 1] != arr[i]) res.append(",");
        }
        res.append(")");
        return res.toString();
    }
}
