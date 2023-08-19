package basics.BaseService.impl;

import basics.BaseService.BaseService;
import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.baseEntity.BaseEntity;
import exceptions.NotFoundException;
import validation.EntityValidator;

import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;


public abstract class BaseServiceImpl<T extends BaseEntity,R extends BaseRepositoryImpl<T>> implements BaseService<T> {

    protected R repository;
    protected EntityTransaction transaction;
    protected Validator validator;

    public BaseServiceImpl(R repository){
        this.repository = repository;
        transaction = repository.getEm().getTransaction();
        validator = EntityValidator.validator;
    }

    public T findById(long id) throws NotFoundException {
            return repository.findById(id).orElseThrow(() ->
                    new NotFoundException(repository.getClassName().getSimpleName() + " not found!"));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll().orElse(null);
    }


    public boolean isValid(T t){
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if(!violations.isEmpty()){
            for(ConstraintViolation<T> c : violations)
                System.out.println(c.getMessage());
            return false;
        }
        return true;
    }
}
