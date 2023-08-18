package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.SalaryReport;
import entity.UniversityStaff;
import exceptions.NotSavedException;
import repository.SalaryReportRepositoryImpl;
import service.SalaryReportService;
import utility.Constants;

public class SalaryReportServiceImpl<T extends UniversityStaff> extends BaseServiceImpl<SalaryReport<T>, SalaryReportRepositoryImpl<T>> implements SalaryReportService<T> {

    public SalaryReportServiceImpl(SalaryReportRepositoryImpl<T> repository) {
        super(repository);
    }

    @Override
    public SalaryReport<T> saveOrUpdate(SalaryReport<T> salaryReport) throws NotSavedException {
        try{
            if(!transaction.isActive()){
                transaction.begin();
                salaryReport=repository.saveOrUpdate(salaryReport).orElseThrow(() -> new NotSavedException(Constants.SALARY_REPORT_SAVE_EXCEPTION));
                transaction.commit();
            }else
                salaryReport = repository.saveOrUpdate(salaryReport).orElseThrow(() -> new NotSavedException(Constants.SALARY_REPORT_SAVE_EXCEPTION));
            return salaryReport;
        } catch (Exception e){
            transaction.rollback();
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public void delete(SalaryReport<T> salaryReport) {
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

}
