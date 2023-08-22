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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee, EmployeeRepositoryImpl> implements EmployeeService {

    private final SalaryReportServiceImpl salaryReportService;

    public EmployeeServiceImpl(EmployeeRepositoryImpl repository) {
        super(repository);
        salaryReportService = ApplicationContext.salaryReportService;
    }

    @Override
    public Employee saveOrUpdate(Employee employee, SalaryReport salaryReport) {
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

    public String getSalaryReport(Employee employee){
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new GregorianCalendar().getTime());
        try {
            return "Report date:\n\t" + date + "\nTotal salary:\n\t " + salaryReportService.getSalaryReport(employee).toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
