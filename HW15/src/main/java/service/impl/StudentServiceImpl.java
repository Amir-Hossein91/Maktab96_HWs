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
        }
    }

    @Override
    public Student saveOrUpdate(Student student, List<Course> courses) throws NotSavedException {
        CourseServiceImpl courseService = ApplicationContext.courseService;
        TeacherService teacherService = ApplicationContext.teacherService;
        try{
            transaction.begin();
            for(Course c : courses) {
//                c = courseService.saveOrUpdate(c);
//                Teacher teacher = teacherService.saveOrUpdate(c.getTeacher(),c.getTeacher().getPresentedCourses().stream().toList(),c.getTeacher().getSalaryReport());
                if(c==null /*|| teacher == null*/)
                    throw new NotSavedException(Constants.STUDENT_SAVE_EXCEPTION);
            }

            student = repository.saveOrUpdate(student).orElseThrow(() -> new NotSavedException(Constants.STUDENT_SAVE_EXCEPTION));
            transaction.commit();
            return student;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
