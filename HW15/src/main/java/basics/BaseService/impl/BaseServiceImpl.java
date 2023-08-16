package basics.BaseService.impl;

import basics.BaseService.BaseService;
import basics.baseRepository.impl.BaseRepositoryImpl;

import javax.persistence.EntityTransaction;


public abstract class BaseServiceImpl<T,R extends BaseRepositoryImpl<T>> implements BaseService<T> {

    protected R repository;
    protected EntityTransaction transaction;

    public BaseServiceImpl(R repository){
        this.repository = repository;
        transaction = repository.getEm().getTransaction();
    }

}
