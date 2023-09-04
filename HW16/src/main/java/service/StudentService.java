package service;

import basics.service.BaseService;
import entity.Student;
import exceptions.InvalidDateException;
import exceptions.NotProperTimeException;

public interface StudentService extends BaseService<Student> {
    Student validateAndSetInformation(Student student) throws InvalidDateException;
    Student setUsernameAndPassword(Student student);
    void showUsernameAndPassword(Student student);
    Student checkUsernameAndPassword(String username, String password);
    boolean canRegister(Student student) throws NotProperTimeException;
    boolean canRepay(Student student) throws NotProperTimeException;
}
