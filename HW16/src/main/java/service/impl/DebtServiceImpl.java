package service.impl;

import basics.service.impl.BaseServiceImpl;
import entity.Debt;
import repository.impl.DebtRepositoryImpl;

public class DebtServiceImpl extends BaseServiceImpl<DebtRepositoryImpl, Debt> {
    public DebtServiceImpl(DebtRepositoryImpl repository) {
        super(repository);
    }
}
