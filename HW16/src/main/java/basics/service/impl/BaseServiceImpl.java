package basics.service.impl;

import basics.repository.impl.BaseRepositoryImpl;
import basics.service.BaseService;
import connection.Connection;
import entity.BankAccount;
import entity.Debt;
import entity.base.BaseEntity;
import exceptions.NotFoundException;
import exceptions.NotSavedException;
import utility.ApplicationContext;
import utility.Printer;
import validation.EntityValidator;

import javax.persistence.EntityTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BaseServiceImpl<R extends BaseRepositoryImpl<T>,T extends BaseEntity> implements BaseService<T> {

    protected R repository;
    protected EntityTransaction transaction;
    protected Printer printer;
    protected Scanner input;

    public BaseServiceImpl (R repository){
        this.repository = repository;
        transaction = Connection.entityManager.getTransaction();
        printer = ApplicationContext.printer;
        input = new Scanner(System.in);
    }

    @Override
    public T saveOrUpdate(T t) {
        try{
            if(!isValid(t))
                return null;
            if(!transaction.isActive()){
                transaction.begin();
                t = repository.saveOrUpdate(t).orElseThrow(()-> new NotSavedException("\nCould not save " + repository.getClassname().getSimpleName()));
                transaction.commit();
            }
            else
                t = repository.saveOrUpdate(t).orElseThrow(()-> new NotSavedException("\nCould not save " + repository.getClassname().getSimpleName()));
            if(t != null && !(t instanceof BankAccount) && !(t instanceof Debt) )
                printer.printMessage(repository.getClassname().getSimpleName()  + " saved successfully!");
            return t;
        } catch (Exception e){
            if(transaction.isActive())
                transaction.rollback();
            printer.printError(e.getMessage());
            e.getStackTrace();
            input.nextLine();
            return null;
        }
    }

    @Override
    public void delete(T t) {
        try{
            if(!isValid(t))
                return;
            if(!transaction.isActive()){
                transaction.begin();
                repository.delete(t);
                transaction.commit();
            }
            else
                repository.delete(t);
        } catch (Exception e){
            if(transaction.isActive())
                transaction.rollback();
            printer.printError("Could not complete deletion. Specified " + repository.getClassname().getSimpleName() + " not found!");
            printer.printError(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public T findById(long id) {
        try{
            return repository.findById(id).orElseThrow(()-> new NotFoundException("\nCould not find " + repository.getClassname().getSimpleName()
                    + " with id = " + id));
        } catch (Exception e){
            printer.printError(e.getMessage());
            return null;
        }
    }

    @Override
    public List<T> findAll() {
        try{
            return repository.findAll().orElseThrow(()-> new NotFoundException("\nThere is no " + repository.getClassname().getSimpleName()
                    + " registered!"));
        }catch (Exception e){
            printer.printError(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean isValid(T t) {
        Validator validator = EntityValidator.validator;
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        if(!violations.isEmpty()){
            for(ConstraintViolation<T> c : violations)
                printer.printError(c.getMessage());
            return false;
        }
        return true;
    }
}
