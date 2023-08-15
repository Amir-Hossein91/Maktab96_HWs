package basics.baseRepository.impl;

import basics.baseRepository.BaseRepository;
import connection.Connection;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BaseRepositoryImpl<T> implements BaseRepository<T> {

    EntityManager em;
    Class<T> className;

    public BaseRepositoryImpl() {
        em = Connection.entityManager;

    }

    @Override
    public T save(T t) {
        return em.merge(t);
    }

    @Override
    public T update(T t) {
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
