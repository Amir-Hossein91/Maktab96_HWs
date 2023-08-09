package question2.repository.impl;

import question2.base.baseReopsitory.impl.BaseRepositoryImpl;
import question2.entity.Teacher;
import question2.repository.TeacherRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class TeacherRepositoryImpl extends BaseRepositoryImpl<Teacher> implements TeacherRepository {
    public TeacherRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public List<Teacher> loadAll() {
        return getEntityManager().createQuery("from Teacher").getResultList();
    }
}
