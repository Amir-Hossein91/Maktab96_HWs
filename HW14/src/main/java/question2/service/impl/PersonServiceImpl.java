package question2.service.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import question2.entity.Person;
import question2.repository.impl.PersonRepositoryImpl;
import question2.service.PersonService;
import question2.utility.ApplicationContext;

import java.util.List;
import java.util.Set;

public class PersonServiceImpl implements PersonService {
    private final PersonRepositoryImpl personRepository;
    private final Validator personValidator;

    public PersonServiceImpl(PersonRepositoryImpl personRepository){
        this.personRepository = personRepository;
        this.personValidator = ApplicationContext.validator;
    }
    @Override
    public Person signUp(String firstname, String lastname){
        Person person = new Person(firstname,lastname);
        if(!isValid(person))
            return null;
        personRepository.getEntityManager().getTransaction().begin();
        person = personRepository.save(Person.class,person);
        personRepository.getEntityManager().getTransaction().commit();
        return person;
    }
    @Override
    public void update(Person person){
        if(!isValid(person))
            return;
        personRepository.getEntityManager().getTransaction().begin();
        try{
            personRepository.update(Person.class,person);
            personRepository.getEntityManager().getTransaction().commit();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void delete(Person person){
        if(!isValid(person))
            return;
        personRepository.getEntityManager().getTransaction().begin();
        try{
            personRepository.delete(Person.class,person);
            personRepository.getEntityManager().getTransaction().commit();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public List<Person> loadAll(){
        return personRepository.loadAll();
    }

    @Override
    public boolean contains(Person person){
        return isValid(person) && personRepository.contains(Person.class,person);
    }

    @Override
    public boolean isValid(Person person) {
        Set<ConstraintViolation<Person>> violations = personValidator.validate(person);
        if(!violations.isEmpty()){
            for(ConstraintViolation<Person> p : violations)
                System.out.println(p.getMessage());
            return false;
        }
        return true;
    }


}
