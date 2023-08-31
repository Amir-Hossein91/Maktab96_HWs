package basics.repository.impl;

import basics.repository.BaseRepository;
import connection.Connection;
import entity.base.BaseEntity;
import lombok.Getter;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Getter
public class BaseRepositoryImpl<T extends BaseEntity> implements BaseRepository<T> {

    protected EntityManager entityManager;
    protected Class<T> classname;

    public BaseRepositoryImpl (Class<T> classname){
        entityManager = Connection.entityManager;
        this.classname = classname;
    }

    @Override
    public Optional<T> saveOrUpdate(T t) {
        if(t.getId()==0)
            entityManager.persist(t);
        else{
            entityManager.find(t.getClass(),t.getId());
            entityManager.merge(t);
        }
        return Optional.of(t);
    }

    @Override
    public void delete(T t) {
        entityManager.remove(t);
    }

    @Override
    public Optional<T> findById(long id) {
        return Optional.ofNullable(entityManager.find(classname,id));
    }

    @Override
    public Optional<List<T>> findAll() {
        String queryLine = "from " + classname.getSimpleName();
        return Optional.ofNullable(entityManager.createQuery(queryLine,classname).getResultList());
    }
}
