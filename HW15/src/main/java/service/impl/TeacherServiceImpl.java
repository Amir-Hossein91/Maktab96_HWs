package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Course;
import entity.SalaryReport;
import entity.Teacher;
import repository.TeacherRepositoryImpl;
import service.TeacherService;

import java.util.List;

public class TeacherServiceImpl extends BaseServiceImpl<Teacher, TeacherRepositoryImpl> implements TeacherService {
    s
    public TeacherServiceImpl(TeacherRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public void delete(Teacher teacher) {

    }

    @Override
    public Teacher findById(long id) {
        return null;
    }

    @Override
    public List<Teacher> findAll() {
        return null;
    }

    @Override
    public Teacher saveOrUpdate(Teacher teacher, List<Course> presentedCourses, SalaryReport salaryReport) {
        return null;
    }
}
