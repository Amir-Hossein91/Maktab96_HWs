package question2.service;

import question2.base.baseService.BaseService;
import question2.entity.Teacher;

public interface TeacherService extends BaseService<Teacher> {

    Teacher signUp(String firstname, String lastname, String teacherCode);
}
