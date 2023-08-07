package qustion2.repository.impl;

import qustion2.base.baseReopsitory.impl.BaseRepositoryImpl;
import qustion2.entity.Student;
import qustion2.repository.StudentRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Student> implements StudentRepository {

    public StudentRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<Student> loadAll() {
        return getEntityManager().createQuery("from Student").getResultList();
    }
}
