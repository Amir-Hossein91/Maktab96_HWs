package service;

import basics.service.impl.BaseServiceImpl;
import entity.Student;
import repository.StudentRepositoryImpl;

public class StudentServiceImpl extends BaseServiceImpl<StudentRepositoryImpl, Student> {
    public StudentServiceImpl(StudentRepositoryImpl repository) {
        super(repository);
    }
}
