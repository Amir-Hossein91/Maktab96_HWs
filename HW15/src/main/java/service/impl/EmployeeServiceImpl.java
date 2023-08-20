package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Employee;
import entity.SalaryReport;
import exceptions.NotFoundException;
import exceptions.NotSavedException;
import repository.EmployeeRepositoryImpl;
import service.EmployeeService;
import utility.ApplicationContext;
import utility.Constants;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee, EmployeeRepositoryImpl> implements EmployeeService {

    public EmployeeServiceImpl(EmployeeRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public Employee saveOrUpdate(Employee employee, SalaryReport salaryReport) {
        SalaryReportServiceImpl salaryReportService = ApplicationContext.salaryReportService;
        try{
            if(!isValid(employee)){
                throw new NotSavedException(Constants.EMPLOYEE_SAVE_EXCEPTION);
            }
            if(!transaction.isActive()){
                transaction.begin();
                salaryReport = salaryReportService.saveOrUpdate(salaryReport);
                employee = repository.saveOrUpdate(employee).orElseThrow(() -> new NotSavedException(Constants.EMPLOYEE_SAVE_EXCEPTION));
                if(salaryReport == null)
                    throw new NotSavedException(Constants.EMPLOYEE_SAVE_EXCEPTION);
                transaction.commit();
            }
            else {
                salaryReport = salaryReportService.saveOrUpdate(salaryReport);
                employee = repository.saveOrUpdate(employee).orElseThrow(() -> new NotSavedException(Constants.EMPLOYEE_SAVE_EXCEPTION));
                if(salaryReport == null)
                    throw new NotSavedException(Constants.EMPLOYEE_SAVE_EXCEPTION);
            }
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

    public SalaryReport getSalaryReport(Employee employee){
        SalaryReportServiceImpl salaryReportService = ApplicationContext.salaryReportService;
        try {
            return salaryReportService.getSalaryReport(employee);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
