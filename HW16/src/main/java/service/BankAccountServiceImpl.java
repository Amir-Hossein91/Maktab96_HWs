package service;

import basics.service.impl.BaseServiceImpl;
import entity.BankAccount;
import repository.BankAccountRepositoryImpl;

public class BankAccountServiceImpl extends BaseServiceImpl<BankAccountRepositoryImpl, BankAccount> {
    public BankAccountServiceImpl(BankAccountRepositoryImpl repository) {
        super(repository);
    }
}
