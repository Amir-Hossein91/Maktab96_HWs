package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Course;
import entity.Student;
import entity.Teacher;
import exceptions.NotSavedException;
import repository.StudentRepositoryImpl;
import service.StudentService;
import service.TeacherService;
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
            throw e;
        }
    }

    @Override
    public Student saveOrUpdate(Student student){
        try{
            if(!transaction.isActive()){
                transaction.begin();
                student = repository.saveOrUpdate(student).orElseThrow(() -> new NotSavedException(Constants.STUDENT_SAVE_EXCEPTION));
                transaction.commit();
            }
            else
                student = repository.saveOrUpdate(student).orElseThrow(() -> new NotSavedException(Constants.STUDENT_SAVE_EXCEPTION));

            return student;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }
}
