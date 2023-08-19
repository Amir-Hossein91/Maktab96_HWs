package repository;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Person;

public class PersonRepositoryImpl extends BaseRepositoryImpl<Person> {
    public PersonRepositoryImpl() {
        super(Person.class);
    }
}
