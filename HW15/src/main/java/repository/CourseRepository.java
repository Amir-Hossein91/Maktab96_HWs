package repository;

import basics.baseRepository.BaseRepository;
import entity.Course;
import entity.Student;

import java.util.List;

public interface CourseRepository extends BaseRepository<Course> {
    List<Course> getCurrentSemesterCourses(Student student);
}
