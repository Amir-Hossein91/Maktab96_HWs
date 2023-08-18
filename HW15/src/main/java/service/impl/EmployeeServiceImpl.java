package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Employee;
import entity.SalaryReport;
import exceptions.NotSavedException;
import repository.EmployeeRepositoryImpl;
import service.EmployeeService;
import utility.ApplicationContext;
import utility.Constants;

import java.util.List;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee, EmployeeRepositoryImpl> implements EmployeeService {

    public EmployeeServiceImpl(EmployeeRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public Employee saveOrUpdate(Employee employee, SalaryReport salaryReport) {
        try{
            SalaryReportServiceImpl salaryReportService = ApplicationContext.salaryReportService;
            transaction.begin();
            salaryReport = salaryReportService.saveOrUpdate(salaryReport);
            if(salaryReport == null)
                throw new NotSavedException(Constants.EMPLOYEE_SAVE_EXCEPTION);
            employee = repository.saveOrUpdate(employee).orElseThrow(() -> new NotSavedException(Constants.EMPLOYEE_SAVE_EXCEPTION));
            transaction.commit();
            return employee;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void delete(Employee employee) {
       try{
           transaction.begin();
           repository.delete(employee);
           transaction.commit();
       }catch(Exception e){
           transaction.rollback();
           System.out.println(e.getMessage());
       }
    }


}
