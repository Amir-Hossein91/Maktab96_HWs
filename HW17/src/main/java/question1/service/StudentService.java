package question1.service;

import question1.base.baseService.BaseService;
import question1.entity.enums.StudyField;
import question1.entity.Student;

import java.util.Date;

public interface StudentService extends BaseService<Student> {

    Student signUp(String firstname, String lastname, String studentCode, StudyField field);
    Student signUp(String firstname, String lastname, Date birthDate, String studentCode, StudyField field,int entranceYear);
}
