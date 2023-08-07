package qustion2.service.impl;

import qustion2.entity.Student;
import qustion2.repository.impl.StudentRepositoryImpl;
import qustion2.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryImpl studentRepository;

    public StudentServiceImpl(StudentRepositoryImpl studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Student signUp(String firstname, String lastname, String studentCode) {
        Student student = new Student(firstname,lastname, studentCode);
        studentRepository.getEntityManager().getTransaction().begin();
        student = studentRepository.save(Student.class,student);
        studentRepository.getEntityManager().getTransaction().commit();
        return student;
    }

    @Override
    public void update(Student student) {
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
        return studentRepository.contains(Student.class,student);
    }
}
