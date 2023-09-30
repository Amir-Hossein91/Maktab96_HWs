package question1.base.baseReopsitory;

import question1.entity.Person;

public interface BaseRepository<T extends Person> {
    T save(Class<T> cname,T t);
    void update (Class<T> cname,T t);
    void delete (Class<T> cname, T t);
    boolean contains (Class<T> cname, T t);
    T findById (Class<T> cname, long id);
}
