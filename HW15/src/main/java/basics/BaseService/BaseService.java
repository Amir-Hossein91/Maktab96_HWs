package basics.BaseService;

import java.util.List;

public interface BaseService<T> {
    T saveOrUpdate(T t);
//    void saveWithoutTransaction(T t);
    void delete(T t);
    T findById (long id);
    List<T> findAll();
}
