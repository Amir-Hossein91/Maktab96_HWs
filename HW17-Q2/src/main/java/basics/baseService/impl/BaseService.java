package basics.baseService.impl;

import basics.baseRepository.impl.BaseRepository;
import conncetion.Connection;
import entity.base.BaseEntity;

import javax.persistence.EntityTransaction;

public class BaseService <R extends BaseRepository<T>, T extends BaseEntity>{

    protected R repository;
    protected EntityTransaction entityTransaction;

    public BaseService(R repository){
        this.repository = repository;
        entityTransaction = Connection.entityManager.getTransaction();
    }

    public T save (T t){
        return repository.save(t).orElseThrow();
    }

}
