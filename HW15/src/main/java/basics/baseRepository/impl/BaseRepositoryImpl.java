package basics.baseRepository.impl;

import basics.baseRepository.BaseRepository;
import connection.Connection;
import entity.baseEntity.BaseEntity;
import lombok.Getter;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Getter
public class BaseRepositoryImpl<T extends BaseEntity> implements BaseRepository<T> {

    private EntityManager em;
    private Class<T> className;

    public BaseRepositoryImpl(Class<T> className) {
        this.className = className;
        em = Connection.entityManager;
    }

    @Override
    public Optional<T> saveOrUpdate(T t) {
        if(findById(t.getId()).isEmpty()){
            em.persist(t);
            return Optional.ofNullable(em.find(className,t.getId()));
        } else{
            return Optional.ofNullable(em.merge(t));
        }
    }

    @Override
    public void delete(T t) {
        em.remove(t);
    }

    @Override
    public Optional<T> findById(long id) {
        return Optional.ofNullable(em.find(className,id));
    }

    @Override
    public Optional<List<T>> findAll() {
        return Optional.ofNullable(em.createQuery("from " + className.getSimpleName(),className).getResultList());
    }
}
