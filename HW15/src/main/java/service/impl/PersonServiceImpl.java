package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Person;
import repository.PersonRepositoryImpl;
import service.PersonService;

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
        transaction.begin();
        person = repository.saveOrUpdate(person).orElse(null);
        transaction.commit();

        return person;
    }
}
