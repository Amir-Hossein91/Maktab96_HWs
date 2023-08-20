package service;

import basics.BaseService.BaseService;
import entity.Course;
import entity.Student;
import exceptions.NotSavedException;

import java.util.List;

public interface StudentService extends BaseService<Student> {

    Student saveOrUpdate(Student student) throws NotSavedException;
}
