package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Course;
import entity.Student;
import entity.Teacher;
import exceptions.NotFoundException;
import exceptions.NotSavedException;
import repository.impl.CourseRepositroyImpl;
import service.CourseService;
import utility.ApplicationContext;
import utility.Constants;

import java.util.List;
import java.util.Set;

public class CourseServiceImpl extends BaseServiceImpl<Course, CourseRepositroyImpl> implements CourseService {

    public CourseServiceImpl(CourseRepositroyImpl repository) {
        super(repository);
    }

    @Override
    public Course saveOrUpdate(Course course) {
        try{
            if(!isValid(course)){
                throw new NotSavedException(Constants.COURSE_SAVE_EXCEPTION);
            }
            if(!transaction.isActive()){
                transaction.begin();
                course = repository.saveOrUpdate(course).orElseThrow(() -> new NotSavedException(Constants.COURSE_SAVE_EXCEPTION));
                transaction.commit();
            } else
                course = repository.saveOrUpdate(course).orElseThrow(() -> new NotSavedException(Constants.COURSE_SAVE_EXCEPTION));
            return course;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
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

    @Override
    public List<Course> getCurrentSemesterCourses(Student student){
        return repository.getCurrentSemesterCourses(student);
    }

    @Override
    public Course findIfPresented (long courseId) throws NotFoundException{
        TeacherServiceImpl teacherService = ApplicationContext.teacherService;

        List<Teacher> teachers = teacherService.findAll();
        List<Set<Course>> sets = teachers.stream().map(Teacher::getPresentedCourses).toList();
        for(Set<Course> c : sets){
            Course[] courses = c.toArray(Course[]::new);
            for(int i=0; i<c.size(); i++){
                if(courses[i].getId()==courseId && courses[i].getSemesterNumber()== Constants.CURRENT_SEMESTER_NUMBER)
                    return courses[i];
            }
        }
        throw new NotFoundException(Constants.NO_TEACHER_PRESENTED_COURSE);
    }

}
