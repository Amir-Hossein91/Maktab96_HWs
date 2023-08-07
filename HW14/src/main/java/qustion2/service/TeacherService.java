package qustion2.service;

import qustion2.base.baseService.BaseService;
import qustion2.entity.Teacher;

public interface TeacherService extends BaseService<Teacher> {

    Teacher signUp(String firstname, String lastname, String teacherCode);
}
