package basics.baseRepository;

import java.util.List;

public interface BaseRepository<T> {
    T saveOrUpdate(T t);
    void delete(T t);
    T findById (long id);
    List<T> findAll();
}