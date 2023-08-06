package qustion2.repository;

import qustion2.entity.Person;

import javax.persistence.EntityManager;
import java.util.List;

public class PersonRepository {

    private EntityManager em;

    public PersonRepository (EntityManager em){
        this.em = em;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public Person save(Person person){
        em.persist(person);
       return em.find(Person.class,person.getId());
    }

    public void update(Person person){
        if(findById(person.getId()) == null)
            throw new IllegalArgumentException("Wrong Entry!");
        em.merge(person);
    }

    public void delete(Person person){
        if(!contains(person))
            throw new IllegalArgumentException("Wrong entry!");
        em.remove(findById(person.getId()));
    }

    public List<Person> loadAll(){
        return em.createQuery("from Person").getResultList();
    }

    public boolean contains(Person person){
        Person fetched = findById(person.getId());
        return person.equals(fetched);
    }

    public Person findById(Long id){
        return em.find(Person.class, id);
    }

}
