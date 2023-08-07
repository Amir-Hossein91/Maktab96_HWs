package qustion2.service.impl;

import qustion2.entity.Person;
import qustion2.entity.Teacher;
import qustion2.repository.impl.TeacherRepositoryImpl;
import qustion2.service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepositoryImpl teacherRepository;

    public TeacherServiceImpl(TeacherRepositoryImpl teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher signUp(String firstname, String lastname, String teacherCode) {
        Teacher teacher = new Teacher(firstname,lastname, teacherCode);
        teacherRepository.getEntityManager().getTransaction().begin();
        teacher = teacherRepository.save(Teacher.class,teacher);
        teacherRepository.getEntityManager().getTransaction().commit();
        return teacher;
    }

    @Override
    public void update(Teacher teacher) {
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
        return teacherRepository.contains(Teacher.class,teacher);
    }


}
