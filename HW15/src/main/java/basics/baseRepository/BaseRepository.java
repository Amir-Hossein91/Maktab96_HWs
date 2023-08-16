package basics.baseRepository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {
    Optional<T> saveOrUpdate(T t);
    void delete(T t);
    Optional<T> findById (long id);
    Optional<List<T>> findAll();
}