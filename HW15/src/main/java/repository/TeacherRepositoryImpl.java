package repository;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.Course;
import entity.Teacher;
import utility.Constants;

import javax.persistence.Query;
import java.util.List;
import java.util.Set;

public class TeacherRepositoryImpl extends BaseRepositoryImpl<Teacher> {
    public TeacherRepositoryImpl() {
        super(Teacher.class);
    }
}
