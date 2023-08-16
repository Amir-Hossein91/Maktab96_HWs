package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Course;
import entity.Score;
import entity.Student;
import exceptions.NotFoundException;
import exceptions.NotSavedException;
import repository.ScoreRepositoryImpl;
import service.ScoreService;
import utility.ApplicationContext;
import utility.Constants;

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
            courseService.findById(course.getId());
            studentService.findById(student.getId());
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
}
