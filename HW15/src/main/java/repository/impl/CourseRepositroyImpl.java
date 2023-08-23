package repository.impl;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Course;
import entity.Student;
import repository.CourseRepository;
import utility.Constants;

import javax.persistence.Query;
import java.util.List;

public class CourseRepositroyImpl extends BaseRepositoryImpl<Course> implements CourseRepository {
    public CourseRepositroyImpl() {
        super(Course.class);
    }

    @Override
    public List<Course> getCurrentSemesterCourses(Student student){
        String jpql = "select c from Course c join Score s on c.id = s.course.id where s.student=:st and c.semesterNumber=:se";
        Query query = getEm().createQuery(jpql,Course.class);
        query.setParameter("se", Constants.CURRENT_SEMESTER_NUMBER);
        query.setParameter("st",student);
        return query.getResultList();
    }
}
