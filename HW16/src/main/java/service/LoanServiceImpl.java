package service;

import basics.service.impl.BaseServiceImpl;
import entity.Loan;
import repository.LoanRepositoryImpl;

public class LoanServiceImpl extends BaseServiceImpl<LoanRepositoryImpl, Loan> {
    public LoanServiceImpl(LoanRepositoryImpl repository) {
        super(repository);
    }
}
