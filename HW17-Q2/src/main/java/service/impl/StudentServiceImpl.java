package service.impl;

import basics.baseService.impl.BaseService;
import entity.Student;
import repository.impl.StudentRepositoryImpl;

public class StudentServiceImpl extends BaseService<StudentRepositoryImpl, Student> {
    public StudentServiceImpl(StudentRepositoryImpl repository) {
        super(repository);
    }
}
