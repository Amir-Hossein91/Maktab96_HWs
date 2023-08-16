package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Course;
import exceptions.NotFoundException;
import exceptions.NotSavedException;
import repository.CourseRepositroyImpl;
import service.CourseService;
import utility.Constants;

import java.util.List;

public class CourseServiceImpl extends BaseServiceImpl<Course, CourseRepositroyImpl> implements CourseService {

    public CourseServiceImpl(CourseRepositroyImpl repository) {
        super(repository);
    }

    @Override
    public Course saveOrUpdate(Course course) throws NotSavedException {
        try{
            if(!transaction.isActive()){
                transaction.begin();
                course = repository.saveOrUpdate(course).orElseThrow(() -> new NotSavedException(Constants.COURSE_SAVE_EXCEPTION));
                transaction.commit();
            } else
                course = repository.saveOrUpdate(course).orElseThrow(() -> new NotSavedException(Constants.COURSE_SAVE_EXCEPTION));
            return course;
        } catch (NotSavedException e){
            transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Course course) {
        if(!transaction.isActive()){
            transaction.begin();
            repository.delete(course);
            transaction.commit();
        } else
            repository.delete(course);
    }

}
