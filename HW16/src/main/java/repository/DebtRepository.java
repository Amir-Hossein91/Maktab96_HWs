package repository;

import basics.repository.BaseRepository;
import entity.Debt;
import entity.Student;

import java.util.List;
import java.util.Optional;

public interface DebtRepository extends BaseRepository<Debt> {
    Optional<List<Debt>> getPaidDebts(Student student);
    Optional<List<Debt>> getUnpaidDebts(Student student);
    Optional<List<Debt>> getMonthlyUnpaidDebts(Student student, int year, int month);
}
