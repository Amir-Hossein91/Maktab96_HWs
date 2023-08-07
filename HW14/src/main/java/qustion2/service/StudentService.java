package qustion2.service;

import qustion2.base.baseService.BaseService;
import qustion2.entity.Student;

public interface StudentService extends BaseService<Student> {

    Student signUp(String firstname, String lastname , String studentCode);
}
