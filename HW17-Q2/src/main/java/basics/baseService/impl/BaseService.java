package basics.baseService.impl;

import basics.baseRepository.impl.BaseRepository;
import conncetion.Connection;
import entity.base.BaseEntity;
import validation.EntityValidator;

import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class BaseService <R extends BaseRepository<T>, T extends BaseEntity>{

    protected R repository;
    protected EntityTransaction transaction;

    public BaseService(R repository){
        this.repository = repository;
        transaction = Connection.entityManager.getTransaction();
    }

    public T save (T t){
        if(!isValid(t))
            return null;

        if(transaction.isActive())
            return repository.save(t).orElseThrow();
        else {
            transaction.begin();
            t = repository.save(t).orElseThrow();
            transaction.commit();
            return t;
        }
    }

    public boolean isValid(T t) {
        Validator validator = EntityValidator.validator;
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if(!violations.isEmpty()){
            for(ConstraintViolation<T> c : violations)
                System.out.println(c.getMessage());
            return false;
        }
        return true;
    }

}
