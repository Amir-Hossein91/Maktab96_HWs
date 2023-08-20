package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.Employee;
import entity.SalaryReport;
import entity.Teacher;
import entity.UniversityStaff;
import exceptions.NotFoundException;
import exceptions.NotSavedException;
import repository.SalaryReportRepositoryImpl;
import service.SalaryReportService;
import utility.ApplicationContext;
import utility.Constants;

import java.util.Optional;

public class SalaryReportServiceImpl extends BaseServiceImpl<SalaryReport, SalaryReportRepositoryImpl> implements SalaryReportService {

    public SalaryReportServiceImpl(SalaryReportRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public SalaryReport saveOrUpdate(SalaryReport salaryReport) throws NotSavedException {
        try{
            if(!transaction.isActive()){
                transaction.begin();
                salaryReport=repository.saveOrUpdate(salaryReport).orElseThrow(() -> new NotSavedException(Constants.SALARY_REPORT_SAVE_EXCEPTION));
                transaction.commit();
            } else{
                salaryReport = repository.saveOrUpdate(salaryReport).orElseThrow(() -> new NotSavedException(Constants.SALARY_REPORT_SAVE_EXCEPTION));
            }
            return salaryReport;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(SalaryReport salaryReport) {
        try{
            if(!transaction.isActive()){
                transaction.begin();
                repository.delete(salaryReport);
                transaction.commit();
            }else
                repository.delete(salaryReport);
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
        }
    }

    public SalaryReport getSalaryReport(UniversityStaff user) throws NotFoundException {
        return repository.getSalaryReport(user).orElseThrow(() -> new NotFoundException("Salary report not found!"));
    }
}
