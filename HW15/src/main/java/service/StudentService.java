package service;

import basics.BaseService.BaseService;
import entity.Student;
import exceptions.NotSavedException;

public interface StudentService extends BaseService<Student> {

    Student saveOrUpdate(Student student) throws NotSavedException;
    Float calculatePreviousSemesterAverage(Student student);
}
