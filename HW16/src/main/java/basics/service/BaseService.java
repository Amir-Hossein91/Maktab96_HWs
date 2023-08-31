package basics.service;

import entity.base.BaseEntity;
import exceptions.NotFoundException;
import exceptions.NotSavedException;

import java.util.List;
import java.util.Optional;

public interface BaseService <T extends BaseEntity> {
    T saveOrUpdate(T t);
    void delete(T t);
    T findById (long id);
   List<T> findAll();
}
