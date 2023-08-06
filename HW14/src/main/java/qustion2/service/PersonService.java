package qustion2.service;

import qustion2.entity.Person;
import qustion2.repository.PersonRepository;

import java.util.List;

public class PersonService {
    private final PersonRepository personRepository;

    public PersonService (PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Person signUp(String firstname, String lastname){
        Person person = new Person(firstname,lastname);
        personRepository.getEntityManager().getTransaction().begin();
        person = personRepository.save(person);
        personRepository.getEntityManager().getTransaction().commit();
        return person;
    }

    public void update(Person person){
        personRepository.getEntityManager().getTransaction().begin();
        try{
            personRepository.update(person);
            personRepository.getEntityManager().getTransaction().commit();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(Person person){
        personRepository.getEntityManager().getTransaction().begin();
        try{
            personRepository.delete(person);
            personRepository.getEntityManager().getTransaction().commit();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Person> loadAll(){
        return personRepository.loadAll();
    }

    public boolean contains(Person person){
        return personRepository.contains(person);
    }


}
