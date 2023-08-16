package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Employee;
import entity.SalaryReport;
import repository.EmployeeRepositoryImpl;
import service.EmployeeService;
import utility.ApplicationContext;

import java.util.List;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee, EmployeeRepositoryImpl> implements EmployeeService {

    public EmployeeServiceImpl(EmployeeRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public Employee saveOrUpdate(Employee employee, SalaryReport salaryReport) {
        SalaryReportServiceImpl salaryReportService = ApplicationContext.salaryReportService;
        transaction.begin();
        salaryReportService.saveOrUpdate(salaryReport);
        employee = repository.saveOrUpdate(employee).orElse(null);
        transaction.commit();

        return employee;
    }

    @Override
    public void delete(Employee employee) {
        transaction.begin();
        repository.delete(employee);
        transaction.commit();
    }

    @Override
    public Employee findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll().orElse(null);
    }

}
