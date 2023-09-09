package service;

import basics.service.BaseService;
import entity.Debt;
import entity.Loan;
import entity.Student;

import java.util.List;

public interface DebtService extends BaseService<Debt> {

    List<Debt> calculateDebts (Loan loan);
    List<Debt> getPaidDebts (Student student);
    List<Debt> getUnpaidDebts (Student student);
    List<Debt> getMonthlyUnpaidDebts(Student student);
    void payDebt(Student student);
}
