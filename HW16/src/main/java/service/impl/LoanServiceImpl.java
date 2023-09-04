package service.impl;

import basics.service.impl.BaseServiceImpl;
import entity.Loan;
import repository.impl.LoanRepositoryImpl;

public class LoanServiceImpl extends BaseServiceImpl<LoanRepositoryImpl, Loan> {
    public LoanServiceImpl(LoanRepositoryImpl repository) {
        super(repository);
    }
}