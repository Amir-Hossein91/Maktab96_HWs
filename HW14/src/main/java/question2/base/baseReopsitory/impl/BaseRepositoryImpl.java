package question2.base.baseReopsitory.impl;

import question2.base.baseReopsitory.BaseRepository;
import question2.entity.Person;

import javax.persistence.EntityManager;

public class BaseRepositoryImpl<T extends Person> implements BaseRepository<T> {

    private EntityManager em;

    public BaseRepositoryImpl(EntityManager em){
        this.em = em;
    }

    public EntityManager getEntityManager() {
        return em;
    }
    @Override
    public T save(Class<T> name,T t) {
        em.persist(t);
        return em.find(name,t.getId());
    }

    @Override
    public void update(Class<T> cname,T t) {
        if(findById(cname,t.getId()) == null)
            throw new IllegalArgumentException("Wrong Entry!");
        em.merge(t);
    }

    @Override
    public void delete(Class<T> cname, T t) {
        if(!contains(cname,t))
            throw new IllegalArgumentException("Wrong entry!");
        em.remove(findById(cname,t.getId()));
    }


    @Override
    public boolean contains(Class<T> cname,T t) {
        Person fetched = findById(cname,t.getId());
        return t.equals(fetched);
    }

    @Override
    public T findById(Class<T> name,long id) {
        return em.find(name, id);
    }
}
