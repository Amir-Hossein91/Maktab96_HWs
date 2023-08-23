package repository;

import basics.baseRepository.BaseRepository;
import entity.Course;
import entity.Score;
import entity.Student;

import java.util.List;

public interface ScoreRepository extends BaseRepository<Score> {
    int getCurrentSemesterCredits(Student student);
    boolean isTaken(Course course, Student student);
    boolean isPassed(Course course,Student student);
    List<Score> getCourseScores(Course course);
    List<Score> getPreviousSemesterScores(Student student);
    List<Score> getCourseHistory(Student student);
}
