package repository;

import basics.repository.impl.BaseRepositoryImpl;
import entity.Student;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Student> {
    public StudentRepositoryImpl(Class<Student> classname) {
        super(classname);
    }
}
