package basics.BaseService.impl;

import basics.BaseService.BaseService;
import basics.baseRepository.impl.BaseRepositoryImpl;
import exceptions.NotFoundException;

import javax.persistence.EntityTransaction;
import java.util.List;


public abstract class BaseServiceImpl<T,R extends BaseRepositoryImpl<T>> implements BaseService<T> {

    protected R repository;
    protected EntityTransaction transaction;

    public BaseServiceImpl(R repository){
        this.repository = repository;
        transaction = repository.getEm().getTransaction();
    }

    public T findById(long id) throws NotFoundException {
            return repository.findById(id).orElseThrow(() ->
                    new NotFoundException(repository.getClassName().getSimpleName() + " not found!"));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll().orElse(null);
    }
}
