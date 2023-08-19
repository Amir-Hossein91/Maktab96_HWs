package basics.BaseService;

import entity.baseEntity.BaseEntity;
import exceptions.NotFoundException;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    void delete(T t);
    T findById (long id) throws NotFoundException;
    List<T> findAll();
    boolean isValid(T t);
}
