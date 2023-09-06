package repository;

import basics.repository.BaseRepository;
import entity.Loan;
import entity.Student;

import java.util.List;

public interface LoanRepository extends BaseRepository<Loan> {

    List<Loan> getLoansOf (Student student);
}
