package question2.service.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import question2.entity.Person;
import question2.entity.Student;
import question2.repository.impl.StudentRepositoryImpl;
import question2.service.StudentService;
import question2.utility.ApplicationContext;

import java.util.List;
import java.util.Set;

public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryImpl studentRepository;
    private final Validator studentValidator;

    public StudentServiceImpl(StudentRepositoryImpl studentRepository){
        this.studentRepository = studentRepository;
        this.studentValidator = ApplicationContext.validator;
    }

    @Override
    public Student signUp(String firstname, String lastname, String studentCode) {
        Student student = new Student(firstname,lastname, studentCode);
        if(!isValid(student))
            return null;
        studentRepository.getEntityManager().getTransaction().begin();
        student = studentRepository.save(Student.class,student);
        studentRepository.getEntityManager().getTransaction().commit();
        return student;
    }

    @Override
    public void update(Student student) {
        if(!isValid(student))
            return;
        studentRepository.getEntityManager().getTransaction().begin();
        try{
            studentRepository.update(Student.class,student);
            studentRepository.getEntityManager().getTransaction().commit();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Student student) {
        if(!isValid(student))
            return;
        studentRepository.getEntityManager().getTransaction().begin();
        try{
            studentRepository.delete(Student.class,student);
            studentRepository.getEntityManager().getTransaction().commit();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Student> loadAll() {
        return studentRepository.loadAll();
    }

    @Override
    public boolean contains(Student student) {
        return isValid(student) && studentRepository.contains(Student.class,student);
    }

    @Override
    public boolean isValid(Student student) {
        Set<ConstraintViolation<Student>> violations = studentValidator.validate(student);
        if(!violations.isEmpty()){
            for(ConstraintViolation<Student> p : violations)
                System.out.println(p.getMessage());
            return false;
        }
        return true;
    }
}
