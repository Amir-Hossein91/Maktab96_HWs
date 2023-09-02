package repository;

import basics.repository.impl.BaseRepositoryImpl;
import entity.Debt;

public class DebtRepositoryImpl extends BaseRepositoryImpl<Debt> {
    public DebtRepositoryImpl(Class<Debt> classname) {
        super(classname);
    }
}
