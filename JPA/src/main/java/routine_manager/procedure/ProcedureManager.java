package routine_manager.procedure;

import dataManagement.DataScope;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import org.eclipse.persistence.asm.Type;
import routine_manager.routine.RoutineParameter;
import routine_manager.routine.RoutineRegisters;

import java.sql.CallableStatement;
import java.sql.Connection;

public class ProcedureManager {

    public static void executeProcedure(String procName, Object[] args) throws Exception {
        try (DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();
            Connection cn = em.unwrap(Connection.class);
            RoutineParameter[] params = RoutineRegisters.getRoutineParams(procName);
            CallableStatement q = cn.prepareCall("call " + procName + arrayToArgs(args));
            for (int i = 0; i < params.length; i++) {
                if(params[i].mode() == ParameterMode.IN) q.setObject(i+1,args[i]);
                else q.registerOutParameter(i+1, );
            }
            q.execute();
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
