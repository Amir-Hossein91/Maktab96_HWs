package qustion2.utility;

import qustion2.repository.PersonRepository;
import qustion2.repository.StudentRepository;
import qustion2.service.PersonService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ApplicationContext {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hw14");
    private static final EntityManager em = emf.createEntityManager();
    private static final PersonRepository pr = new PersonRepository(em);
    private static final StudentRepository sr = new StudentRepository();


    public static final PersonService personService = new PersonService(pr);


}
