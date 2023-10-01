package question1.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import question1.entity.enums.StudyField;
import question1.repository.impl.StudentRepositoryImpl;
import question1.entity.Student;
import question1.service.StudentService;
import question1.validation.EntityValidator;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryImpl studentRepository;
    private final Validator studentValidator;
    private final Logger logger;

    public StudentServiceImpl(StudentRepositoryImpl studentRepository){
        this.studentRepository = studentRepository;
        this.studentValidator = EntityValidator.validator;
        logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    }

    @Override
    public Student signUp(String firstname, String lastname, String studentCode, StudyField field) {
        logger.info("Attempt to sign up a student: " + firstname + " " + lastname + " with no DOB specified");
        Student student = new Student(firstname,lastname, studentCode,field);
        if(!isValid(student))
            return null;
        studentRepository.getEntityManager().getTransaction().begin();
        student = studentRepository.save(Student.class,student);
        studentRepository.getEntityManager().getTransaction().commit();
        logger.info("Student signup completed");
        return student;
    }

    @Override
    public Student signUp(String firstname, String lastname, Date birthDate, String studentCode, StudyField field, int entranceYear) {
        logger.info("Attempt to sign up a student: " + firstname + " " + lastname + " with DOB = " + birthDate);
        Student student = new Student(firstname,lastname,birthDate,studentCode,field,entranceYear);
        if(!isValid(student))
            return null;
        studentRepository.getEntityManager().getTransaction().begin();
        student = studentRepository.save(Student.class,student);
        studentRepository.getEntityManager().getTransaction().commit();
        logger.info("Student signed up successfully");
        return student;
    }

    @Override
    public void update(Student student) {
        logger.info("Attempt to update information of student with id = " + student.getId());
        if(!isValid(student))
            return;
        studentRepository.getEntityManager().getTransaction().begin();
        try{
            studentRepository.update(Student.class,student);
            studentRepository.getEntityManager().getTransaction().commit();
            logger.info("Information updated successfully");
        }catch (IllegalArgumentException e){
            logger.error("Student not found: " + e);
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Student student) {
        logger.info("Attempt to delete student " +student.getFirstname() + " " + student.getLastname() +
                " with claimed id of: " + student.getId());
        if(!isValid(student))
            return;
        studentRepository.getEntityManager().getTransaction().begin();
        try{
            studentRepository.delete(Student.class,student);
            studentRepository.getEntityManager().getTransaction().commit();
            logger.info("Successfully deleted student with id= " +student.getId() +": "
                    + student.getFirstname() + " " + student.getLastname());
        } catch (IllegalArgumentException e){
            logger.error("Student not found: " + e);
            System.out.println(e.getMessage());
        } catch (PersistenceException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Student> loadAll() {
        logger.info("Attempt to get all Students information");
        try{
            return studentRepository.loadAll();
        } catch (PersistenceException e) {
            logger.error("loading Student information list failed: " + e);
            return null;
        }
    }

    @Override
    public boolean contains(Student student) {
        logger.info("Attempt to check if Student " + student.getFirstname() + " " + student.getLastname() + " exists");
        return isValid(student) && studentRepository.contains(Student.class,student);
    }

    @Override
    public boolean isValid(Student student) {
        Set<ConstraintViolation<Student>> violations = studentValidator.validate(student);
        if(!violations.isEmpty()){
            for(ConstraintViolation<Student> p : violations) {
                logger.error("Invalid information: " + p.getMessage());
                System.out.println(p.getMessage());
            }
            return false;
        }
        return true;
    }
}
