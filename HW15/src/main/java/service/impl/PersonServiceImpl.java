package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Person;
import exceptions.NotFoundException;
import exceptions.NotSavedException;
import repository.PersonRepositoryImpl;
import service.PersonService;
import utility.Constants;

import javax.persistence.NoResultException;
import java.util.List;

public class PersonServiceImpl extends BaseServiceImpl<Person, PersonRepositoryImpl> implements PersonService {

    public PersonServiceImpl(PersonRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public void delete(Person person) {
        transaction.begin();
        repository.delete(person);
        transaction.commit();
    }

    @Override
    public Person saveOrUpdate(Person person) {
        try{
            transaction.begin();
            person = repository.saveOrUpdate(person).orElseThrow(() -> new NotSavedException(Constants.PERSON_SAVE_EXCEPTION));
            transaction.commit();
            return person;

        }catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Person findUser(String username, String password) throws NotFoundException {
        try{
       return repository.findUser(username,password).orElseThrow(()->new NotFoundException("Username or password is incorrect"));
        } catch (NoResultException e){
            throw new NotFoundException("Username or password is incorrect");
        }
    }
}
