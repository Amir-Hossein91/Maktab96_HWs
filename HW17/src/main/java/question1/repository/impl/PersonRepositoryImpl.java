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

    private final Logger logger;

    public PersonRepositoryImpl(EntityManager em) {
        super(em);
        logger = LoggerFactory.getLogger(PersonRepositoryImpl.class);
    }

    @Override
    public List<Person> loadAll() {
        try {
            return getEntityManager().createQuery("select p from Person p").getResultList();
        } catch (PersistenceException e){
            logger.error("All information load failure: " + e + "-->" + e.getMessage());
            return null;
        }
    }
}
