package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Course;
import entity.Student;
import exceptions.NotSavedException;
import repository.StudentRepositoryImpl;
import service.StudentService;
import utility.ApplicationContext;

import java.util.List;

public class StudentServiceImpl extends BaseServiceImpl<Student, StudentRepositoryImpl> implements StudentService {

    public StudentServiceImpl(StudentRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public void delete(Student student) {
        transaction.begin();
        repository.delete(student);
        transaction.commit();
    }

    @Override
    public Student saveOrUpdate(Student student, List<Course> courses) throws NotSavedException {
        CourseServiceImpl courseService = ApplicationContext.courseService;
        transaction.begin();
        for(Course c : courses) {
            courseService.saveOrUpdate(c);
            student.getTakenUnits().add(c);
        }
    }
}
