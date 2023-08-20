package repository;

import basics.baseRepository.impl.BaseRepositoryImpl;
import entity.SalaryReport;
import entity.UniversityStaff;

import javax.persistence.Query;
import java.util.Optional;

public class SalaryReportRepositoryImpl extends BaseRepositoryImpl<SalaryReport> {
    public SalaryReportRepositoryImpl() {
        super(SalaryReport.class);
    }

    public Optional<SalaryReport> getSalaryReport(UniversityStaff user){
        String jpql = "select s from SalaryReport s where s.owner =:user";
        Query query = getEm().createQuery(jpql);
        query.setParameter("user", user);
        return Optional.ofNullable((SalaryReport)query.getSingleResult());
    }
}
