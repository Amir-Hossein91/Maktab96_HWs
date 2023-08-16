package service;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Course;
import repository.CourseRepositroyImpl;

import java.util.List;

public class CourseServiceImpl extends BaseServiceImpl<Course, CourseRepositroyImpl> {

    public CourseServiceImpl(CourseRepositroyImpl repository) {
        super(repository);
    }

    @Override
    public Course saveOrUpdate(Course course) {
        if(!transaction.isActive()){
            transaction.begin();
            course = repository.saveOrUpdate(course);
            transaction.commit();
        } else
            course = repository.saveOrUpdate(course);
        return course;
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

    @Override
    public Course findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return repository.findAll();
    }
}
