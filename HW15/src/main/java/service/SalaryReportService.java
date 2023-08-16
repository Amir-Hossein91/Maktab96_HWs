package service;

import basics.BaseService.BaseService;
import entity.SalaryReport;

public interface SalaryReportService extends BaseService<SalaryReport> {

    SalaryReport saveOrUpdate(SalaryReport salaryReport);
}
