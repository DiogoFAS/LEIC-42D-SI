package dataManagement;

import interfaces.IMapper;
import jakarta.persistence.EntityManager;

import java.lang.reflect.Method;

public class Mapper<T, TId> implements IMapper<T, TId> {

    private final Class<T> tClass;
    private final Class<TId> tIdClass;

    public Mapper(Class<T> tClass, Class<TId> tIdClass) {
        this.tClass = tClass;
        this.tIdClass = tIdClass;
    }

    @Override
    public TId create(T e) throws Exception {
        try (DataScope scope = new DataScope()) {
            scope.getEntityManager().persist(e);
            scope.validateWork();
            return getPK(e);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public T read(TId id) throws Exception {
        try (DataScope scope = new DataScope()) {
            return scope.getEntityManager().find(tClass, id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public void update(T e) throws Exception {
        try (DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();
            if (em.contains(e)) throw new java.lang.IllegalAccessException("Entity not found.");
            em.merge(e);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public void delete(T e) throws Exception {
        try (DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();
            if (em.contains(e)) throw new java.lang.IllegalAccessException("Entity not found.");
            em.remove(e);
            em.getTransaction().commit();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    private TId getPK(T e) {
        try {
            Method idGetter = tClass.getMethod("getId");
            return (TId) idGetter.invoke(e);
        } catch (Exception ex) {
            return null;
        }
    }
}

