package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Course;
import entity.Student;
import exceptions.NotFoundException;
import exceptions.NotSavedException;
import repository.CourseRepositroyImpl;
import service.CourseService;
import utility.ApplicationContext;
import utility.Constants;

import java.util.List;

public class CourseServiceImpl extends BaseServiceImpl<Course, CourseRepositroyImpl> implements CourseService {

    public CourseServiceImpl(CourseRepositroyImpl repository) {
        super(repository);
    }

    @Override
    public Course saveOrUpdate(Course course) {
//        TeacherServiceImpl teacherService = ApplicationContext.teacherService;
        try{
            if(!transaction.isActive()){
                transaction.begin();
//                teacherService.saveOrUpdate(course.getTeacher());
                course = repository.saveOrUpdate(course).orElseThrow(() -> new NotSavedException(Constants.COURSE_SAVE_EXCEPTION));
                transaction.commit();
            } else
                course = repository.saveOrUpdate(course).orElseThrow(() -> new NotSavedException(Constants.COURSE_SAVE_EXCEPTION));
            return course;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Course course) {
        try{
            if(!transaction.isActive()){
                transaction.begin();
                repository.delete(course);
                transaction.commit();
            } else
                repository.delete(course);
        }catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }
    }

    public List<Course> getCurrentSemesterCourses(Student student){
        return repository.getCurrentSemesterCourses(student);
    }

}
