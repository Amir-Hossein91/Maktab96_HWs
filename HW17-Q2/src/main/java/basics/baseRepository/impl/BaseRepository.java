package basics.baseRepository.impl;

import conncetion.Connection;
import entity.base.BaseEntity;

import javax.persistence.EntityManager;
import java.util.Optional;

public class BaseRepository <T extends BaseEntity>{

    protected EntityManager entityManager;

    public BaseRepository(){
        entityManager = Connection.entityManager;
    }

    public Optional<T> save (T t) {
        entityManager.persist(t);
        return Optional.of(t);
    }
}
