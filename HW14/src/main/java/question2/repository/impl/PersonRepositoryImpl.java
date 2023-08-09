package question2.repository.impl;

import question2.base.baseReopsitory.impl.BaseRepositoryImpl;
import question2.entity.Person;
import question2.repository.PersonRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class PersonRepositoryImpl extends BaseRepositoryImpl<Person> implements PersonRepository {

    public PersonRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<Person> loadAll() {
        return getEntityManager().createQuery("from Person").getResultList();
    }
}
