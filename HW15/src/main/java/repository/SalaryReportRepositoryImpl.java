package repository;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.SalaryReport;
import entity.UniversityStaff;

public class SalaryReportRepositoryImpl extends BaseRepositoryImpl<SalaryReport> {
    public SalaryReportRepositoryImpl() {
        super(SalaryReport.class);
    }
}
