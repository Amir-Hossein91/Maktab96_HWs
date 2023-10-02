package question1.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import question1.entity.Teacher;
import question1.entity.enums.Degree;
import question1.entity.enums.FacultyLevel;
import question1.repository.impl.TeacherRepositoryImpl;
import question1.service.TeacherService;
import question1.validation.EntityValidator;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositoryImpl teacherRepository;
    private final Validator teacherValidator;
    private final Logger logger;

    public TeacherServiceImpl(TeacherRepositoryImpl teacherRepository){
        this.teacherRepository = teacherRepository;
        this.teacherValidator = EntityValidator.validator;
        logger = LoggerFactory.getLogger(TeacherServiceImpl.class);
    }

    @Override
    public Teacher signUp(String firstname, String lastname, Date birthDate, String teacherCode, Degree degree, FacultyLevel facultyLevel, double salary) {
        logger.info("Attempt to sign up a teacher: " + firstname + " " + lastname + " with specified salary");
        Teacher teacher = new Teacher(firstname,lastname,birthDate,teacherCode, degree, facultyLevel, salary);
        if(!isValid(teacher))
            return null;
        teacherRepository.getEntityManager().getTransaction().begin();
        teacher = teacherRepository.save(Teacher.class,teacher);
        teacherRepository.getEntityManager().getTransaction().commit();
        logger.info("Teacher signup completed successfully");
        return teacher;
    }

    @Override
    public Teacher signUp(String firstname, String lastname, String teacherCode, Degree degree, FacultyLevel facultyLevel) {
        logger.info("Attempt to sign up a teacher: " + firstname + " " + lastname + " with specified salary");
        Teacher teacher = new Teacher(firstname,lastname,teacherCode, degree, facultyLevel);
        if(!isValid(teacher))
            return null;
        teacherRepository.getEntityManager().getTransaction().begin();
        teacher = teacherRepository.save(Teacher.class,teacher);
        teacherRepository.getEntityManager().getTransaction().commit();
        logger.info("Teacher signup completed successfully");
        return teacher;
    }

    @Override
    public void update(Teacher teacher) {
        logger.info("Attempt to update information of teacher with id = " + teacher.getId());
        if(!isValid(teacher))
            return;
        teacherRepository.getEntityManager().getTransaction().begin();
        try{
            teacherRepository.update(Teacher.class,teacher);
            teacherRepository.getEntityManager().getTransaction().commit();
            logger.info("Information updated successfully");
        }catch (IllegalArgumentException e){
            logger.error("Teacher not found: " + e);
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Teacher teacher) {
        logger.info("Attempt to delete teacher " +teacher.getFirstname() + " " + teacher.getLastname() +
                " with claimed id of: " + teacher.getId());
        if(!isValid(teacher))
            return;
        teacherRepository.getEntityManager().getTransaction().begin();
        try{
            teacherRepository.delete(Teacher.class,teacher);
            teacherRepository.getEntityManager().getTransaction().commit();
            logger.info("Successfully deleted teacher with id= " +teacher.getId() +": "
                    + teacher.getFirstname() + " " + teacher.getLastname());
        } catch (IllegalArgumentException e){
            logger.error("Student not found: " + e);
            System.out.println(e.getMessage());
        } catch (PersistenceException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Teacher> loadAll() {
        logger.info("Attempt to get all teachers information");
        try {
            return teacherRepository.loadAll();
        } catch (PersistenceException e){
            logger.error("loading Teacher information list failed: " + e);
            return null;
        }
    }

    @Override
    public boolean contains(Teacher teacher) {
        logger.info("Attempt to check if Teacher " + teacher.getFirstname() + " " + teacher.getLastname() + " exists");
        return isValid(teacher) && teacherRepository.contains(Teacher.class,teacher);
    }

    @Override
    public boolean isValid(Teacher teacher) {
        Set<ConstraintViolation<Teacher>> violations = teacherValidator.validate(teacher);
        if(!violations.isEmpty()){
            for(ConstraintViolation<Teacher> p : violations) {
                logger.error("Invalid information: " + p.getMessage());
                System.out.println(p.getMessage());
            }
            return false;
        }
        return true;
    }


}
