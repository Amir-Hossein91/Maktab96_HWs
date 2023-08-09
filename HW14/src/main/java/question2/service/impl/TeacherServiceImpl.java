package question2.service.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import question2.entity.Student;
import question2.entity.Teacher;
import question2.repository.impl.TeacherRepositoryImpl;
import question2.service.TeacherService;
import question2.utility.ApplicationContext;

import java.util.List;
import java.util.Set;

public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositoryImpl teacherRepository;
    private final Validator teacherValidator;

    public TeacherServiceImpl(TeacherRepositoryImpl teacherRepository){
        this.teacherRepository = teacherRepository;
        this.teacherValidator = ApplicationContext.validator;
    }

    @Override
    public Teacher signUp(String firstname, String lastname, String teacherCode) {
        Teacher teacher = new Teacher(firstname,lastname, teacherCode);
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
