package basics.BaseService;

import exceptions.NotFoundException;

import java.util.List;

public interface BaseService<T> {
    void delete(T t);
    T findById (long id) throws NotFoundException;
    List<T> findAll();
}
