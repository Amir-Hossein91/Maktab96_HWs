package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Person;
import repository.PersonRepository;

import javax.persistence.Query;
import java.util.Optional;


public class PersonRepositoryImpl extends BaseRepositoryImpl<Person> implements PersonRepository {
    public PersonRepositoryImpl() {
        super(Person.class);
    }

    @Override
    public Optional<Person> findUser(String username, String password){
        String sql = "select p from Person p where p.username = :username and p.password = :password";
        Query query = getEm().createQuery(sql,Person.class);
        query.setParameter("username",username);
        query.setParameter("password", password);
        return Optional.ofNullable((Person) query.getSingleResult());
    }
}
