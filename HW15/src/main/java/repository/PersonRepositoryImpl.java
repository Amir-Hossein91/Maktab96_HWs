package repository;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Person;

import javax.persistence.Query;
import java.util.Optional;


public class PersonRepositoryImpl extends BaseRepositoryImpl<Person> {
    public PersonRepositoryImpl() {
        super(Person.class);
    }

    public Optional<Person> findUser(String username, String password){
        String sql = "select p from Person p where p.username = :username and p.password = :password";
        Query query = getEm().createQuery(sql,Person.class);
        query.setParameter("username",username);
        query.setParameter("password", password);
        return Optional.ofNullable((Person) query.getSingleResult());
    }
}
