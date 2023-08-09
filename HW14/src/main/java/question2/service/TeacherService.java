package question2.service;

import question2.base.baseService.BaseService;
import question2.entity.Degree;
import question2.entity.FacultyLevel;
import question2.entity.Teacher;

import java.util.Date;

public interface TeacherService extends BaseService<Teacher> {

    Teacher signUp(String firstname, String lastname, Date birthDate, String teacherCode, Degree degree, FacultyLevel facultyLevel,double salary);

    Teacher signUp(String firstname, String lastname, String teacherCode, Degree degree, FacultyLevel facultyLevel);
}
