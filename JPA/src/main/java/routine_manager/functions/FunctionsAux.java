package routine_manager.functions;


import dataManagement.DataScope;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;

public class FunctionsAux {
    public static Object executeFunction(String funName, Object[] args) {
        try (DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();
            StoredProcedureQuery f = em.createStoredProcedureQuery(funName);
            FunctionParameter[] params = Functions.getFunctionParam(funName);
            for (int i = 0; i < params.length; i++) {
                f.registerStoredProcedureParameter(i + 1, params[i].clazz(), params[i].mode());
            }
            for (int i = 0; i < args.length; i++) {
                f.setParameter(i + 1, args[i]);
            }
            f.execute();
            if(Functions.returnsTable(funName)) {
                return f.getResultList();
            }
            return f.getOutputParameterValue(args.length + 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
