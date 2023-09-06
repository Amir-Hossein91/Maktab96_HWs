package service;

import basics.service.BaseService;
import entity.Loan;
import entity.Student;

import java.util.List;
import java.util.Map;

public interface LoanService extends BaseService<Loan> {
    Loan chooseLoan (Loan loan);
    Map<Integer,Loan> getPossibleLoans(Student student);
    List<Loan> getLoansOf(Student student);
}
