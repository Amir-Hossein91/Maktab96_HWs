package service;

import basics.BaseService.BaseService;
import entity.Course;
import entity.Student;
import exceptions.NotFoundException;

import java.util.List;

public interface CourseService extends BaseService<Course> {

    Course saveOrUpdate(Course course);
    List<Course> getCurrentSemesterCourses(Student student);
    Course findIfPresented (long courseId) throws NotFoundException;
}
