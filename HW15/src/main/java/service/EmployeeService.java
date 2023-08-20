package service;

import basics.BaseService.BaseService;
import entity.Employee;
import entity.SalaryReport;

public interface EmployeeService extends BaseService<Employee> {

    Employee saveOrUpdate(Employee employee, SalaryReport salaryReport);
}
