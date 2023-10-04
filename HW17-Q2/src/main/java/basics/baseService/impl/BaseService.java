package basics.baseService.impl;

import basics.baseRepository.impl.BaseRepository;
import conncetion.Connection;
import entity.base.BaseEntity;

import javax.persistence.EntityTransaction;

public class BaseService <R extends BaseRepository<T>, T extends BaseEntity>{

    protected R repository;
    protected EntityTransaction transaction;

    public BaseService(R repository){
        this.repository = repository;
        transaction = Connection.entityManager.getTransaction();
    }

    public T save (T t){
        if(transaction.isActive())
            return repository.save(t).orElseThrow();
        else {
            transaction.begin();
            t = repository.save(t).orElseThrow();
            transaction.commit();
            return t;
        }
    }

}
