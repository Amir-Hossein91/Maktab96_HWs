package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Employee;
import entity.SalaryReport;
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
    public Employee saveOrUpdate(Employee employee/*, SalaryReport salaryReport*/) {
        SalaryReportServiceImpl salaryReportService = ApplicationContext.salaryReportService;
        try{
            if(!transaction.isActive()){
                transaction.begin();
                employee = repository.saveOrUpdate(employee).orElseThrow(() -> new NotSavedException(Constants.EMPLOYEE_SAVE_EXCEPTION));
                SalaryReport salaryReport = salaryReportService.saveOrUpdate(employee.getSalaryReport());
                if(salaryReport == null)
                    throw new NotSavedException(Constants.EMPLOYEE_SAVE_EXCEPTION);
                transaction.commit();
            }
            else {
                employee = repository.saveOrUpdate(employee).orElseThrow(() -> new NotSavedException(Constants.EMPLOYEE_SAVE_EXCEPTION));
                SalaryReport salaryReport = salaryReportService.saveOrUpdate(employee.getSalaryReport());
                if(salaryReport == null)
                    throw new NotSavedException(Constants.EMPLOYEE_SAVE_EXCEPTION);
            }
            return employee;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
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
