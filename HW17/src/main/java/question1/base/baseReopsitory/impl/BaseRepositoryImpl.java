package question1.base.baseReopsitory.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import question1.base.baseReopsitory.BaseRepository;
import question1.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public class BaseRepositoryImpl<T extends Person> implements BaseRepository<T> {

    private EntityManager em;
    private final Logger logger;

    public BaseRepositoryImpl(EntityManager em){
        this.em = em;
        logger = LoggerFactory.getLogger(BaseRepositoryImpl.class);
    }

    public EntityManager getEntityManager() {
        return em;
    }
    @Override
    public T save(Class<T> name,T t) {
        try{
            em.persist(t);
            return em.find(name,t.getId());
        } catch (PersistenceException e){
            logger.error(name + "save failure: " + e);
            return null;
        }
    }

    @Override
    public void update(Class<T> cname,T t) {

        if(findById(cname,t.getId()) == null)
            throw new IllegalArgumentException("Wrong Entry!");
        try{
            em.merge(t);
        } catch (PersistenceException e){
            logger.error(cname + "update failure: " + e);
        }
    }

    @Override
    public void delete(Class<T> cname, T t) {
        if(!contains(cname,t))
            throw new IllegalArgumentException("Wrong entry!");
        try{
            em.remove(findById(cname,t.getId()));
        } catch (PersistenceException e) {
            logger.error(cname + "delete failure: " + e);
            throw e;
        }
    }


    @Override
    public boolean contains(Class<T> cname,T t) {
            T fetched = findById(cname, t.getId());
            return t.equals(fetched);
    }

    @Override
    public T findById(Class<T> name,long id) {
        try {
            return em.find(name, id);
        } catch (PersistenceException e){
            logger.error(name + "delete failure: " + e);
            return null;
        }
    }
}
