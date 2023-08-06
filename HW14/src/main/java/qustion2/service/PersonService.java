package qustion2.service;

import qustion2.entity.Person;
import qustion2.repository.PersonRepository;

import javax.persistence.EntityManager;

public class PersonService {
    private final EntityManager em;
    private final PersonRepository personRepository;

    public PersonService (PersonRepository personRepository, EntityManager em){
        this.personRepository = personRepository;
        this.em = em;
    }

    public Person signUp(String firstname, String lastname){
        Person person = new Person(firstname,lastname);
        em.getTransaction().begin();
        person = personRepository.save(person,em);
        em.getTransaction().commit();
        return person;
    }


}
