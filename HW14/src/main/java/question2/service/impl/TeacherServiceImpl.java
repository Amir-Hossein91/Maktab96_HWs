package question2.service.impl;


import question2.entity.Degree;
import question2.entity.FacultyLevel;
import question2.entity.Teacher;
import question2.repository.impl.TeacherRepositoryImpl;
import question2.service.TeacherService;
import question2.validation.EntityValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositoryImpl teacherRepository;
    private final Validator teacherValidator;

    public TeacherServiceImpl(TeacherRepositoryImpl teacherRepository){
        this.teacherRepository = teacherRepository;
        this.teacherValidator = EntityValidator.validator;
    }

    @Override
    public Teacher signUp(String firstname, String lastname, Date birthDate, String teacherCode, Degree degree, FacultyLevel facultyLevel, double salary) {
        Teacher teacher = new Teacher(firstname,lastname,birthDate,teacherCode, degree, facultyLevel, salary);
        if(!isValid(teacher))
            return null;
        teacherRepository.getEntityManager().getTransaction().begin();
        teacher = teacherRepository.save(Teacher.class,teacher);
        teacherRepository.getEntityManager().getTransaction().commit();
        return teacher;
    }

    @Override
    public Teacher signUp(String firstname, String lastname, String teacherCode, Degree degree, FacultyLevel facultyLevel) {
        Teacher teacher = new Teacher(firstname,lastname,teacherCode, degree, facultyLevel);
        if(!isValid(teacher))
            return null;
        teacherRepository.getEntityManager().getTransaction().begin();
        teacher = teacherRepository.save(Teacher.class,teacher);
        teacherRepository.getEntityManager().getTransaction().commit();
        return teacher;
    }

    @Override
    public void update(Teacher teacher) {
        if(!isValid(teacher))
            return;
        teacherRepository.getEntityManager().getTransaction().begin();
        try{
            teacherRepository.update(Teacher.class,teacher);
            teacherRepository.getEntityManager().getTransaction().commit();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Teacher teacher) {
        if(!isValid(teacher))
            return;
        teacherRepository.getEntityManager().getTransaction().begin();
        try{
            teacherRepository.delete(Teacher.class,teacher);
            teacherRepository.getEntityManager().getTransaction().commit();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Teacher> loadAll() {
        return teacherRepository.loadAll();
    }

    @Override
    public boolean contains(Teacher teacher) {
        return isValid(teacher) && teacherRepository.contains(Teacher.class,teacher);
    }

    @Override
    public boolean isValid(Teacher teacher) {
        Set<ConstraintViolation<Teacher>> violations = teacherValidator.validate(teacher);
        if(!violations.isEmpty()){
            for(ConstraintViolation<Teacher> p : violations)
                System.out.println(p.getMessage());
            return false;
        }
        return true;
    }


}
