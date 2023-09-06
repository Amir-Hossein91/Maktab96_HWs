package repository.impl;

import basics.repository.impl.BaseRepositoryImpl;
import entity.Loan;
import entity.Student;
import repository.LoanRepository;

import javax.persistence.Query;
import java.util.List;

public class LoanRepositoryImpl extends BaseRepositoryImpl<Loan> implements LoanRepository {
    public LoanRepositoryImpl(Class<Loan> classname) {
        super(classname);
    }

    @Override
    public List<Loan> getLoansOf(Student student) {
        String hql = "select l from Loan l where borrower=:s";
        Query query = entityManager.createQuery(hql,Loan.class);
        query.setParameter("s",student);
        return query.getResultList();
    }
}
