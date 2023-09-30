package question1.repository.impl;

import question1.base.baseReopsitory.impl.BaseRepositoryImpl;
import question1.entity.Teacher;
import question1.repository.TeacherRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class TeacherRepositoryImpl extends BaseRepositoryImpl<Teacher> implements TeacherRepository {
    public TeacherRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<Teacher> loadAll() {
        return getEntityManager().createQuery("select t from Teacher t").getResultList();
    }
}
