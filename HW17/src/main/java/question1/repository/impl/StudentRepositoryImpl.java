package question1.repository.impl;

import question1.base.baseReopsitory.impl.BaseRepositoryImpl;
import question1.repository.StudentRepository;
import question1.entity.Student;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Student> implements StudentRepository {

    public StudentRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<Student> loadAll() {
        return getEntityManager().createQuery("select s from Student s").getResultList();
    }
}
