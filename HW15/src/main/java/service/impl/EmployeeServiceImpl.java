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
            //attention to the exception that could be thrown here...
            salaryReport = salaryReportService.saveOrUpdate(salaryReport);
            if(salaryReport == null)
                throw new NotSavedException("???????????");
            employee = repository.saveOrUpdate(employee).orElseThrow(() -> new NotSavedException(Constants.EMPLOYEE_SAVE_EXCEPTION));
            transaction.commit();
            return employee;
        } catch (NotSavedException e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }




    }

    @Override
    public void delete(Employee employee) {
        transaction.begin();
        repository.delete(employee);
        transaction.commit();
    }


}
