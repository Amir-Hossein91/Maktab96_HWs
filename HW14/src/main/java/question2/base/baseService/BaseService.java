package question2.base.baseService;

import question2.entity.Person;

import java.util.List;

public interface BaseService<T extends Person> {
    void update (T t);
    void delete (T t);
    List<T> loadAll();
    boolean contains (T t);

}
