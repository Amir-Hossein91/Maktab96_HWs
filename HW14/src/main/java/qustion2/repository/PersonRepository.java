package qustion2.repository;

import qustion2.entity.Person;

import javax.persistence.EntityManager;

public class PersonRepository {


    public void save(Person person, EntityManager em){
        em.persist(person);
    }
}
