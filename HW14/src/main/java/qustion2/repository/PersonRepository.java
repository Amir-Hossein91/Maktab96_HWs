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
        if(findById(person.getId(), em) == null)
            throw new IllegalArgumentException("Wrong Entry!");
        em.merge(person);
    }

    public void delete(Person person, EntityManager em){
        if(!contains(person,em))
            throw new IllegalArgumentException("Wrong entry!");
        em.remove(findById(person.getId(), em));
    }

    public List<Person> loadAll(EntityManager em){
        return em.createQuery("from Person").getResultList();
    }

    public boolean contains(Person person, EntityManager em){
        Person fetched = findById(person.getId(), em);
        return person.equals(fetched);
    }

    public Person findById(Long id, EntityManager em){
        return em.find(Person.class, id);
    }

}
