package basics.BaseService;

import java.util.List;

public interface BaseService<T> {
    T save(T t);
    T update(T t);
    void delete(T t);
    T findById (long id);
    List<T> findAll();
}
