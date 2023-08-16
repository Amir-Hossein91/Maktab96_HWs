package basics.baseRepository.impl;

import basics.baseRepository.BaseRepository;
import connection.Connection;
import lombok.Getter;

import javax.persistence.EntityManager;
import java.util.List;

@Getter
public class BaseRepositoryImpl<T> implements BaseRepository<T> {

    private final EntityManager em;
    private Class<T> className;

    public BaseRepositoryImpl() {
        em = Connection.entityManager;
    }

    @Override
    public T saveOrUpdate(T t) {
        return em.merge(t);
    }

    @Override
    public void delete(T t) {
        em.remove(t);
    }

    @Override
    public T findById(long id) {
        return em.find(className,id);
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("from " + className.getSimpleName(),className).getResultList();
    }
}
