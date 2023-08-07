package qustion2.repository.impl;

import qustion2.base.baseReopsitory.impl.BaseRepositoryImpl;
import qustion2.entity.Teacher;
import qustion2.repository.TeacherRepository;

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
