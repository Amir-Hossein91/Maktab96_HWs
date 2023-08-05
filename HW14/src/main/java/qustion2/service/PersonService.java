package qustion2.service;

import qustion2.entity.Person;
import qustion2.repository.PersonRepository;
import qustion2.utility.ApplicationContext;

import javax.persistence.EntityManager;

public class PersonService {
    private final EntityManager em;
    private final PersonRepository personRepository;

    public PersonService (PersonRepository personRepository, EntityManager em){
        this.personRepository = personRepository;
        this.em = em;
    }

    public void save(Person person){
        em.getTransaction().begin();
        personRepository.save(person,em);
        em.getTransaction().commit();
    }
}
