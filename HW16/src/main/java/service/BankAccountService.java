package service;

import basics.service.BaseService;
import entity.BankAccount;
import exceptions.NotFoundException;

public interface BankAccountService extends BaseService<BankAccount> {
    BankAccount validateAndSetInformation(BankAccount bankAccount);
    BankAccount findByCardNumber(String cardNumber) throws NotFoundException;
}
