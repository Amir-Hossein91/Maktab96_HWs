package service;

import basics.service.BaseService;
import entity.Student;
import exceptions.InvalidDateException;

public interface StudentService extends BaseService<Student> {
    Student validateAndSetInformation(Student student) throws InvalidDateException;
    Student setUsernameAndPassword(Student student);
    void showUsernameAndPassword(Student student);
    void checkUsernameAndPassword();
}
