package service;

import basics.BaseService.BaseService;
import entity.SalaryReport;
import entity.UniversityStaff;
import exceptions.NotFoundException;
import exceptions.NotSavedException;

public interface SalaryReportService extends BaseService<SalaryReport> {

    SalaryReport saveOrUpdate(SalaryReport salaryReport);
    SalaryReport getSalaryReport(UniversityStaff user) throws NotFoundException;
}
