package question2.service;

import question2.base.baseService.BaseService;
import question2.entity.Student;
import question2.entity.enums.StudyField;

import java.util.Date;

public interface StudentService extends BaseService<Student> {

    Student signUp(String firstname, String lastname, String studentCode, StudyField field);
    Student signUp(String firstname, String lastname, Date birthDate, String studentCode, StudyField field,int entranceYear);
}
