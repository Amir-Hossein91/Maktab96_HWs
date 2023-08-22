package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Course;
import entity.Score;
import entity.Student;
import exceptions.NotSavedException;
import repository.StudentRepositoryImpl;
import service.StudentService;
import utility.ApplicationContext;
import utility.Constants;

import java.util.List;

public class StudentServiceImpl extends BaseServiceImpl<Student, StudentRepositoryImpl> implements StudentService {

    private final ScoreServiceImpl scoreService;

    public StudentServiceImpl(StudentRepositoryImpl repository) {
        super(repository);
        scoreService = ApplicationContext.scoreService;
    }

    @Override
    public void delete(Student student) {
        try{
            transaction.begin();
            repository.delete(student);
            transaction.commit();
        } catch(Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public Student saveOrUpdate(Student student){
        try{
            if(!isValid(student)){
                throw new NotSavedException(Constants.STUDENT_SAVE_EXCEPTION);
            }
            if(!transaction.isActive()){
                transaction.begin();
                student = repository.saveOrUpdate(student).orElseThrow(() -> new NotSavedException(Constants.STUDENT_SAVE_EXCEPTION));
                transaction.commit();
            }
            else
                student = repository.saveOrUpdate(student).orElseThrow(() -> new NotSavedException(Constants.STUDENT_SAVE_EXCEPTION));

            return student;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Float calculatePreviousSemesterAverage(Student student){
        try{
            List<Score> scores = scoreService.getPreviousSemesterScores(student);
            if (scores==null)
                return 0F;
            float totalCredits = scores.stream()
                    .map(Score::getCourse)
                    .map(Course::getCredits)
                    .reduce(0,Integer::sum);
            float sum = scores.stream()
                    .map(score-> score.getCourse().getCredits() * score.getValue())
                    .reduce(0F,Float::sum);
            return sum/totalCredits;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0F;
        }
    }
}
