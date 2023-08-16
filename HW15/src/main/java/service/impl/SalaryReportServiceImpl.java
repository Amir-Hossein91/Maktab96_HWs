package service.impl;

import basics.BaseService.impl.BaseServiceImpl;
import entity.SalaryReport;
import repository.SalaryReportRepositoryImpl;
import service.SalaryReportService;

import java.util.List;

public class SalaryReportServiceImpl extends BaseServiceImpl<SalaryReport, SalaryReportRepositoryImpl> implements SalaryReportService {

    public SalaryReportServiceImpl(SalaryReportRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public SalaryReport saveOrUpdate(SalaryReport salaryReport) {
        if(!transaction.isActive()){
            transaction.begin();
            salaryReport=repository.saveOrUpdate(salaryReport).orElse(null);
            transaction.commit();
        }else
            salaryReport = repository.saveOrUpdate(salaryReport).orElse(null);

        return salaryReport;
    }

    @Override
    public void delete(SalaryReport salaryReport) {
        if(!transaction.isActive()){
            transaction.begin();
            repository.delete(salaryReport);
            transaction.commit();
        }else
            repository.delete(salaryReport);
    }

}
