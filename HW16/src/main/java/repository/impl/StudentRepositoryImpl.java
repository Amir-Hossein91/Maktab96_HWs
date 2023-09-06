package repository.impl;

import basics.repository.impl.BaseRepositoryImpl;
import entity.BankAccount;
import entity.Student;
import entity.enums.AcademicGrade;
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

    @Override
    public Student findByNationalCode(String nationalCode) {
        String hql = "select s from Student s where s.nationalCode =:n ";
        Query query = entityManager.createQuery(hql, Student.class);
        query.setParameter("n",nationalCode);
        return (Student) query.getSingleResult();
    }

    @Override
    public BankAccount findBankAccount(Student student) {
        String hql = "select s.bankAccount from Student s where s.id =:i ";
        Query query = entityManager.createQuery(hql);
        query.setParameter("i",student.getId());
        return (BankAccount) query.getSingleResult();
    }


}
