package qustion2.service;

import qustion2.entity.Person;
import qustion2.repository.PersonRepository;

import javax.persistence.EntityManager;
import java.util.List;

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

    public void update(Person person){
        em.getTransaction().begin();
        try{
            personRepository.update(person,em);
            em.getTransaction().commit();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(Person person){
        em.getTransaction().begin();
        try{
            personRepository.delete(person,em);
            em.getTransaction().commit();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Person> loadAll(){
        return personRepository.loadAll(em);
    }

    public boolean contains(Person person){
        return personRepository.contains(person,em);
    }


}
