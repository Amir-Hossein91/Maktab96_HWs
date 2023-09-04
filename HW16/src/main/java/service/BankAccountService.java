package service;

import basics.service.BaseService;
import entity.BankAccount;

public interface BankAccountService extends BaseService<BankAccount> {
    BankAccount validateAndSetInformation(BankAccount bankAccount);
}
