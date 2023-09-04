package repository.impl;

import basics.repository.impl.BaseRepositoryImpl;
import entity.Student;
import exceptions.NotFoundException;
import repository.StudentRepository;
import utility.Constants;

import javax.persistence.Query;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Student> implements StudentRepository {
    public StudentRepositoryImpl(Class<Student> classname) {
        super(classname);
    }

    @Override
    public Student checkUsernameAndPassword(String username, String password) throws NotFoundException {
        String hql = "select s from Student s where s.username =:u and s.password=:p";
        Query query = entityManager.createQuery(hql, Student.class);
        query.setParameter("u",username);
        query.setParameter("p", password);
        return (Student) query.getSingleResult();
    }
}
