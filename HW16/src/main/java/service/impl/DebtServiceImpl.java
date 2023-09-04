package service.impl;

import basics.service.impl.BaseServiceImpl;
import entity.Debt;
import repository.DebtRepositoryImpl;

public class DebtServiceImpl extends BaseServiceImpl<DebtRepositoryImpl, Debt> {
    public DebtServiceImpl(DebtRepositoryImpl repository) {
        super(repository);
    }
}
