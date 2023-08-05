package qustion2.repository;

import qustion2.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersonRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hw14");
    private final EntityManager em = emf.createEntityManager();

    public void save(Person person){
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
    }
}
