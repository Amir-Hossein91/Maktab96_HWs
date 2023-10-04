package service.impl;

import basics.baseService.impl.BaseService;
import entity.StudentCourseRating;
import repository.impl.StudentCourseRatingRepositoryImpl;

public class StudentCourseRatingServiceImpl extends BaseService<StudentCourseRatingRepositoryImpl, StudentCourseRating> {
    public StudentCourseRatingServiceImpl(StudentCourseRatingRepositoryImpl repository) {
        super(repository);
    }
}
