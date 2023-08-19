package repository;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Teacher;

public class TeacherRepositoryImpl extends BaseRepositoryImpl<Teacher> {
    public TeacherRepositoryImpl() {
        super(Teacher.class);
    }
}
