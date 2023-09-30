package question1.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import question1.entity.Person;
import question1.repository.impl.PersonRepositoryImpl;
import question1.service.PersonService;
import question1.validation.EntityValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

public class PersonServiceImpl implements PersonService {
    private final PersonRepositoryImpl personRepository;
    private final Validator personValidator;
    private final Logger logger;


    public PersonServiceImpl(PersonRepositoryImpl personRepository){
        this.personRepository = personRepository;
        this.personValidator = EntityValidator.validator;
        logger = LoggerFactory.getLogger(PersonServiceImpl.class);
    }
    @Override
    public Person signUp(String firstname, String lastname){
        logger.info("Attempt to signup: " + firstname + " " + lastname);
        Person person = new Person(firstname,lastname);
        if(!isValid(person))
            return null;
        personRepository.getEntityManager().getTransaction().begin();
        person = personRepository.save(Person.class,person);
        personRepository.getEntityManager().getTransaction().commit();
        logger.info("Sign up successful: " + firstname + " " + lastname);
        return person;
    }
    @Override
    public void update(Person person){
        logger.info("Attempt to update information of id = " + person.getId());
        if(!isValid(person))
            return;
        personRepository.getEntityManager().getTransaction().begin();
        try{
            personRepository.update(Person.class,person);
            personRepository.getEntityManager().getTransaction().commit();
            logger.info("update successful");
        }catch (IllegalArgumentException e){
            logger.error("Person not found: " + e);
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void delete(Person person){
        logger.info("Attempt to delete " + person.getFirstname() + " " + person.getLastname() +
                " with claimed id of: " + person.getId());
        if(!isValid(person))
            return;
        personRepository.getEntityManager().getTransaction().begin();
        try{
            personRepository.delete(Person.class,person);
            personRepository.getEntityManager().getTransaction().commit();
            logger.info("Successfully deleted id " + person.getId() + ": " +person.getFirstname() + " " + person.getLastname());
        } catch (IllegalArgumentException e){
            logger.error("Person not found: " + e);
            System.out.println(e.getMessage());
        }
    }
    @Override
    public List<Person> loadAll(){
        logger.info("Attempt to get all Person information");
        return personRepository.loadAll();
    }

    @Override
    public boolean contains(Person person){
        logger.info("Attempt to check if " +person.getFirstname() + " " + person.getLastname() + " exists");
        return isValid(person) && personRepository.contains(Person.class,person);
    }

    @Override
    public boolean isValid(Person person) {
        Set<ConstraintViolation<Person>> violations = personValidator.validate(person);
        if(!violations.isEmpty()){
            for(ConstraintViolation<Person> p : violations) {
                logger.error("Invalid information: " + p.getMessage());
                System.out.println(p.getMessage());
            }
            return false;
        }
        return true;
    }
}
