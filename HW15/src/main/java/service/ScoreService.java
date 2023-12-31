package service;

import basics.BaseService.BaseService;
import entity.Course;
import entity.Score;
import entity.Student;

import java.util.List;

public interface ScoreService extends BaseService<Score> {

    Score saveOrUpdate (Score score, Student student, Course course);
    int getCurrentSemesterCredits(Student student);
    boolean isTaken(Course course,Student student);
    boolean isPassed(Course course,Student student);
    List<Score> getCourseScores(Course course);
    List<Score> getPreviousSemesterScores(Student student);
    List<String> getCourseHistory(Student student);
}
