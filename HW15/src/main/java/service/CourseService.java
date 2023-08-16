package service;

import basics.BaseService.BaseService;
import entity.Course;
import exceptions.NotSavedException;

public interface CourseService extends BaseService<Course> {

    Course saveOrUpdate(Course course) throws NotSavedException;
}
