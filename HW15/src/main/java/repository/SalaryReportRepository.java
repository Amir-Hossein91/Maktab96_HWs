package repository;

import basics.baseRepository.BaseRepository;
import entity.SalaryReport;
import entity.UniversityStaff;

import java.util.Optional;

public interface SalaryReportRepository extends BaseRepository<SalaryReport> {
    Optional<SalaryReport> getSalaryReport(UniversityStaff user);
}
