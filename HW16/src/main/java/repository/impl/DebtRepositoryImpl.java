package repository.impl;

import basics.repository.impl.BaseRepositoryImpl;
import com.github.mfathi91.time.PersianDate;
import entity.Debt;
import entity.Student;
import repository.DebtRepository;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DebtRepositoryImpl extends BaseRepositoryImpl<Debt> implements DebtRepository {
    public DebtRepositoryImpl(Class<Debt> classname) {
        super(classname);
    }

    @Override
    public Optional<List<Debt>> getPaidDebts(Student student) {
        String hql = "select d from Debt d join Loan l on d.loan.id = l.id where l.borrower.id =:s and d.isPaid =:p";
        Query query = entityManager.createQuery(hql, Debt.class);
        query.setParameter("s",student.getId());
        query.setParameter("p", true);
        return Optional.ofNullable(query.getResultList());
    }

    @Override
    public Optional<List<Debt>> getUnpaidDebts(Student student) {
        String hql = "select d from Debt d join Loan l on d.loan.id = l.id where l.borrower.id =:s and d.isPaid =:p";
        Query query = entityManager.createQuery(hql, Debt.class);
        query.setParameter("s",student.getId());
        query.setParameter("p", false);
        return Optional.ofNullable(query.getResultList());
    }


    @Override
    public Optional<List<Debt>> getMonthlyUnpaidDebts(Student student, int year, int month) {
        LocalDate localDate = PersianDate.of(year,month,1).toGregorian();
        String hql = "select d from Debt d join Loan l on d.loan.id = l.id where l.borrower.id =:s and " +
                "d.isPaid =:p and year(d.dueDate) =:y and month(d.dueDate) =:m";
        Query query = entityManager.createQuery(hql, Debt.class);
        query.setParameter("s",student.getId());
        query.setParameter("p", false);
        query.setParameter("y", localDate.getYear());
        query.setParameter("m", localDate.getMonthValue());
        return Optional.ofNullable(query.getResultList());
    }

    @Override
    public Optional<List<Debt>> findByLoanId(Long loanId) {
        String hql = "select d from Debt d  where d.loan.id =:l";
        Query query = entityManager.createQuery(hql, Debt.class);
        query.setParameter("l",loanId);
        return Optional.ofNullable(query.getResultList());
    }
}
