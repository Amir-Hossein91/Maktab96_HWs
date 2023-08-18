package service;

import basics.BaseService.BaseService;
import entity.SalaryReport;
import entity.UniversityStaff;
import exceptions.NotSavedException;

public interface SalaryReportService<T extends UniversityStaff> extends BaseService<SalaryReport<T>> {

    SalaryReport<T> saveOrUpdate(SalaryReport<T> salaryReport) throws NotSavedException;
}
