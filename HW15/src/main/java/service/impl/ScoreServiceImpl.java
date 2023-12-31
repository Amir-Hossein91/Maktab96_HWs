package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Course;
import entity.Score;
import entity.Student;
import exceptions.NotFoundException;
import exceptions.NotSavedException;
import repository.impl.ScoreRepositoryImpl;
import service.ScoreService;
import utility.ApplicationContext;
import utility.Constants;

import java.util.List;

public class ScoreServiceImpl extends BaseServiceImpl<Score, ScoreRepositoryImpl> implements ScoreService {

    public ScoreServiceImpl(ScoreRepositoryImpl repository) {
        super(repository);
    }


    @Override
    public void delete(Score score) {
        if(!transaction.isActive()) {
            transaction.begin();
            repository.delete(score);
            transaction.commit();
        } else
            repository.delete(score);
    }

    @Override
    public Score saveOrUpdate(Score score, Student student, Course course) {
        StudentServiceImpl studentService = ApplicationContext.studentService;
        CourseServiceImpl courseService = ApplicationContext.courseService;
        try{
            if(!isValid(score)){
                throw new NotSavedException(Constants.SCORE_SAVE_EXCEPTION);
            }
            courseService.findById(course.getId());
            studentService.findById(student.getId());
            if(course.getSemesterNumber()!= Constants.CURRENT_SEMESTER_NUMBER)
                throw new NotSavedException(Constants.COURSE_NOT_PRESENTED);
            transaction.begin();
            score = repository.saveOrUpdate(score).orElseThrow(() -> new NotSavedException(Constants.SCORE_SAVE_EXCEPTION));
            transaction.commit();
            return score;
        } catch (NotFoundException e){
            System.out.println(e.getMessage());
            return null;
        } catch (NotSavedException e) {
            transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int getCurrentSemesterCredits(Student student){
        return repository.getCurrentSemesterCredits(student);
    }

    @Override
    public boolean isTaken(Course course,Student student){
        try{
            if(repository.isTaken(course,student))
                throw new NotSavedException(Constants.COURSE_TAKEN);
            return false;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return true;
        }
    }

    @Override
    public boolean isPassed(Course course,Student student){
        try{
            if(repository.isPassed(course, student))
                throw new NotSavedException(Constants.COURSE_PASSED);
            return false;
        } catch(Exception e){
            System.out.println(e.getMessage());
            return true;
        }
    }

    @Override
    public List<Score> getCourseScores(Course course){
        return repository.getCourseScores(course);
    }

    @Override
    public List<Score> getPreviousSemesterScores(Student student){
        try {
            List<Score> result = repository.getPreviousSemesterScores(student);
            if(result.isEmpty())
                throw new NotFoundException(Constants.NO_COURSE_PREVIOUS_SEMESTER);
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }


    }

    @Override
    public List<String> getCourseHistory(Student student){
        try{
            List<Score> scores = repository.getCourseHistory(student);
           return scores.stream().map(score -> score.getCourse().toString() +
                   " ,score = " + score.getValue()).toList();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
