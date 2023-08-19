package service;

import basics.BaseService.BaseService;
import entity.Course;
import entity.SalaryReport;
import entity.Teacher;

import java.util.List;
import java.util.Set;

public interface TeacherService extends BaseService<Teacher> {

    Teacher saveOrUpdate(Teacher teacher, List<Course> presentedCourses, SalaryReport salaryReport);

}
