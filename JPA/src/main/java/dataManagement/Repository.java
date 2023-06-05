package dataManagement;

import interfaces.IRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Repository<T, TId> implements IRepository<T, TId> {

    private final Mapper<T, TId> mapper;

    public Repository(Class<T> entityClass, Class<TId> entityPKClass) {
        this.mapper = new Mapper<>(entityClass, entityPKClass);
    }

    @Override
    public List<T> getAll(String Statement, Class<T> entityClass) throws Exception {
        try (DataScope scope = new DataScope()) {
            EntityManager em = scope.getEntityManager();
            TypedQuery<T> query = em.createNamedQuery(Statement, entityClass);
            scope.validateWork();
            return query.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public T find(TId k) throws Exception {
        try (DataScope scope = new DataScope()) {
            scope.validateWork();
            return mapper.read(k);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public void add(T entity) throws Exception {
        try (DataScope scope = new DataScope()) {
            mapper.create(entity);
            scope.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void delete(T entity) throws Exception {
        try (DataScope scope = new DataScope()) {
            mapper.delete(entity);
            scope.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public void save(T entity) throws Exception {
        try (DataScope scope = new DataScope()) {
            mapper.update(entity);
            scope.validateWork();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
