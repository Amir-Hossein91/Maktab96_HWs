package basics.baseRepository;

import java.util.List;

public interface BaseRepository<T> {
    T save(T t);
    T update(T t);
    void delete(T t);
    T findById (long id);
    List<T> findAll();
}