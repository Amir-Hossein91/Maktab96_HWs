package question2.service.impl;

import question2.entity.Person;
import question2.repository.impl.PersonRepositoryImpl;
import question2.service.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {
    private final PersonRepositoryImpl personRepository;

    public PersonServiceImpl(PersonRepositoryImpl personRepository){
        this.personRepository = personRepository;
    }

    public Person signUp(String firstname, String lastname){
        Person person = new Person(firstname,lastname);
        personRepository.getEntityManager().getTransaction().begin();
        person = personRepository.save(Person.class,person);
        personRepository.getEntityManager().getTransaction().commit();
        return person;
    }

    public void update(Person person){
        personRepository.getEntityManager().getTransaction().begin();
        try{
            personRepository.update(Person.class,person);
            personRepository.getEntityManager().getTransaction().commit();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(Person person){
        personRepository.getEntityManager().getTransaction().begin();
        try{
            personRepository.delete(Person.class,person);
            personRepository.getEntityManager().getTransaction().commit();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Person> loadAll(){
        return personRepository.loadAll();
    }
//
    public boolean contains(Person person){
        return personRepository.contains(Person.class,person);
    }


}
