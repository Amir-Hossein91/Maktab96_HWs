package service;

import basics.BaseService.BaseService;
import entity.Course;
import entity.SalaryReport;
import entity.Teacher;

import java.util.List;

public interface TeacherService extends BaseService<Teacher> {

    Teacher saveOrUpdate(Teacher teacher, List<Course> presentedCourses, SalaryReport salaryReport);
    String getSalaryReport(Teacher teacher);

}
