package service;

import basics.BaseService.BaseService;
import entity.SalaryReport;
import exceptions.NotSavedException;

public interface SalaryReportService extends BaseService<SalaryReport> {

    SalaryReport saveOrUpdate(SalaryReport salaryReport) throws NotSavedException;
}
