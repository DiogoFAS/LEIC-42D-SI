package routine_manager.procedure;

import dataManagement.DataScope;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import routine_manager.routine.RoutineParameter;
import routine_manager.routine.RoutineRegisters;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLType;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class ProcedureManager {
    private static final Map<Class<?>, Integer> SQLmap = new HashMap<>(Map.of(
            int.class, Types.INTEGER,
            String.class, Types.VARCHAR
    ));

    public static Object executeProcedure(String procName, Object[] args) throws Exception {
        try (DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();
            Connection cn = em.unwrap(Connection.class);
            RoutineParameter[] params = RoutineRegisters.getRoutineParams(procName);
            CallableStatement q = cn.prepareCall("call " + procName + arrayToArgs(params));
            for (int i = 0; i < params.length; i++) {
                if(params[i].mode() == ParameterMode.IN) q.setObject(i+1,args[i]);
                else q.registerOutParameter(i+1, SQLmap.get(params[i].clazz()));
            }
            q.execute();
            scope.validateWork();
            return q.getObject(args.length + 1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    private static String arrayToArgs(Object[] arr) {
        StringBuilder res = new StringBuilder("(");
        for (int i = 0; i < arr.length; i++) {
            res.append("?"); //+ (i + 1)
            if (arr[arr.length - 1] != arr[i]) res.append(",");
        }
        res.append(")");
        return res.toString();
    }
}
