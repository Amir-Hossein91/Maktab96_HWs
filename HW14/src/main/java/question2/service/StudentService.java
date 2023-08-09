package question2.service;

import question2.base.baseService.BaseService;
import question2.entity.Student;

public interface StudentService extends BaseService<Student> {

    Student signUp(String firstname, String lastname , String studentCode);
}
