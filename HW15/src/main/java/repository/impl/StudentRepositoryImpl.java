package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Student;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Student> {
    public StudentRepositoryImpl() {
        super(Student.class);
    }
}
