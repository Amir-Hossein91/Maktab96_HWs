package repository;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Course;
import entity.Score;
import entity.Student;
import utility.Constants;

import javax.persistence.Query;
import java.util.List;
import java.util.stream.Stream;

public class ScoreRepositoryImpl extends BaseRepositoryImpl<Score> {
    public ScoreRepositoryImpl() {
        super(Score.class);
    }

    public int getCurrentSemesterCredits(Student student){
        String jpql = "select c.credits from Score s join Course c on c.id = s.course.id where s.student=:st and c.semesterNumber=:se";
//        String jpql = "select s.course.credits from Score s join Course c on s.course.id = c.id where s.student=:st and c.semesterNumber=:se";
        Query query = getEm().createQuery(jpql,Integer.class);
        query.setParameter("se",Constants.CURRENT_SEMESTER_NUMBER);
        query.setParameter("st",student);
        List<Integer> temp = query.getResultList();
        return temp.stream().reduce(0, Integer::sum);
    }

    public boolean isTaken(Course course, Student student){
        String jpql = "select c.credits from Score s join Course c on c.id = s.course.id where s.student=:student and s.course=:course and c.semesterNumber=:semester";
        Query query = getEm().createQuery(jpql);
        query.setParameter("student",student);
        query.setParameter("course",course);
        query.setParameter("semester", Constants.CURRENT_SEMESTER_NUMBER);
        return !query.getResultList().isEmpty();
//        return false;
    }

    public boolean isPassed(Course course,Student student){
        String jpql = "select c.credits from Score s join Course c on c.id = s.course.id where s.student=:student and s.course=:course and s.isPassed=true";
        Query query = getEm().createQuery(jpql);
        query.setParameter("student",student);
        query.setParameter("course",course);
        return !query.getResultList().isEmpty();
//        return false;
    }

    public List<Score> getCourseScores(Course course){
        String jpql = "select s from Score s where s.course=:course";
        Query query = getEm().createQuery(jpql,Score.class);
        query.setParameter("course",course);
        return query.getResultList();
    }
}
