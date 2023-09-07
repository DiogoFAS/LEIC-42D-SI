package routine_manager.query;

import dataManagement.DataScope;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import routine_manager.function.FunctionManager;
import table_returns.JogadorPontos;

import java.util.logging.Logger;

public class QueryManager {

    private static final Logger logger = Logger.getLogger(FunctionManager.class.getName());

    public static<T> T executeNamedQuery(String queryName, Class<?> returnClass, Object ...args) throws Exception {
        try (DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();
            Query res = em.createNamedQuery(queryName, returnClass);
            for (int i = 0; i < args.length; i++) {
                res.setParameter(i + 1, args[i]);
            }
            return (T) res.getResultList();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            throw e;
        }
    }
}
