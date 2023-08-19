package repository;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Course;

public class CourseRepositroyImpl extends BaseRepositoryImpl<Course> {
    public CourseRepositroyImpl() {
        super(Course.class);
    }
}
