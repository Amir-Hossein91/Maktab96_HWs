package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Course;
import entity.Student;
import exceptions.NotSavedException;
import repository.StudentRepositoryImpl;
import service.StudentService;
import utility.ApplicationContext;
import utility.Constants;

import java.util.List;

public class StudentServiceImpl extends BaseServiceImpl<Student, StudentRepositoryImpl> implements StudentService {

    public StudentServiceImpl(StudentRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public void delete(Student student) {
        try{
            transaction.begin();
            repository.delete(student);
            transaction.commit();
        } catch(Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Student saveOrUpdate(Student student, List<Course> courses) throws NotSavedException {
        CourseServiceImpl courseService = ApplicationContext.courseService;
        try{
            transaction.begin();
            for(Course c : courses) {
                c = courseService.saveOrUpdate(c);
                if(c==null)
                    throw new NotSavedException(Constants.STUDENT_SAVE_EXCEPTION);
            }
            student = repository.saveOrUpdate(student).orElseThrow(() -> new NotSavedException(Constants.STUDENT_SAVE_EXCEPTION));
            transaction.commit();
            return student;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }
}
