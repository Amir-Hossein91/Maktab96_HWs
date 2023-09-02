package repository;

import basics.repository.impl.BaseRepositoryImpl;
import entity.BankAccount;

public class BankAccountRepositoryImpl extends BaseRepositoryImpl<BankAccount> {
    public BankAccountRepositoryImpl(Class<BankAccount> classname) {
        super(classname);
    }
}
