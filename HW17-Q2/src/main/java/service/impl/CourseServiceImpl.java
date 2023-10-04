package service.impl;

import basics.baseService.impl.BaseService;
import entity.Course;
import repository.impl.CourseRepositoryImpl;

public class CourseServiceImpl extends BaseService<CourseRepositoryImpl, Course> {
    public CourseServiceImpl(CourseRepositoryImpl repository) {
        super(repository);
    }
}
