package question1.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import question1.base.baseReopsitory.impl.BaseRepositoryImpl;
import question1.entity.Person;
import question1.repository.PersonRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

public class PersonRepositoryImpl extends BaseRepositoryImpl<Person> implements PersonRepository {


    public PersonRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<Person> loadAll() {
            return getEntityManager().createQuery("select p from Person p").getResultList();
    }
}
