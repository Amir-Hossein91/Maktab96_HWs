package repository;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.SalaryReport;
import entity.UniversityStaff;

public class SalaryReportRepositoryImpl<T extends UniversityStaff> extends BaseRepositoryImpl<SalaryReport<T>> {
}
