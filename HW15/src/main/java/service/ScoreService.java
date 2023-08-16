package service;

import basics.BaseService.BaseService;
import entity.Course;
import entity.Score;
import entity.Student;

public interface ScoreService extends BaseService<Score> {

    Score saveOrUpdate (Score score, Student student, Course course);
}
