package question1.service;

import question1.base.baseService.BaseService;
import question1.entity.Teacher;
import question1.entity.enums.Degree;
import question1.entity.enums.FacultyLevel;

import java.util.Date;

public interface TeacherService extends BaseService<Teacher> {

    Teacher signUp(String firstname, String lastname, Date birthDate, String teacherCode, Degree degree, FacultyLevel facultyLevel, double salary);

    Teacher signUp(String firstname, String lastname, String teacherCode, Degree degree, FacultyLevel facultyLevel);
}
