package qustion2.repository;

import qustion2.entity.Person;

import javax.persistence.EntityManager;
import java.util.List;

public class PersonRepository {


    public Person save(Person person, EntityManager em){
        em.persist(person);
       return em.find(Person.class,person.getId());
    }

    public void update(Person person, EntityManager em){
        em.merge(person);
    }

    public void delete(Person person, EntityManager em){
        em.remove(person);
    }

    public List<Person> loadAll(EntityManager em){
        return em.createQuery("from Person").getResultList();
    }

}
