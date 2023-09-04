package repository.impl;

import basics.repository.impl.BaseRepositoryImpl;
import entity.Loan;

public class LoanRepositoryImpl extends BaseRepositoryImpl<Loan> {
    public LoanRepositoryImpl(Class<Loan> classname) {
        super(classname);
    }
}
