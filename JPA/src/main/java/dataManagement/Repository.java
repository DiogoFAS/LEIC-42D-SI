package dataManagement;

import interfaces.IMapper;
import interfaces.IRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;

import java.util.List;

public class Repository<T, TId> implements IRepository<T, TId> {

   private final Mapper<T, TId> mapper;

    public Repository(Mapper<T, TId> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<T> getAll() throws Exception {
        return null;
    }

    @Override
    public T find(TId k) throws Exception {
        return mapper.read(k);
    }

    @Override
    public void add(T entity) throws Exception {
        // TODO
    }

    public void delete(T e) throws Exception {
        try {
            em.getTransaction().begin();
            Object e1 = em.find(e.getClass(), e.getNumal(), LockModeType.PESSIMISTIC_WRITE);
            if (e1 == null)
                throw new IllegalAccessException("Entity not found.");
            em.remove(e1);
            em.getTransaction().commit();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public void save(Object e) throws Exception {

    }
}
