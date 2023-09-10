package service.impl;

import basics.service.impl.BaseServiceImpl;
import entity.BankAccount;
import entity.enums.Bank;
import exceptions.NotFoundException;
import net.bytebuddy.implementation.bytecode.Throw;
import repository.impl.BankAccountRepositoryImpl;
import service.BankAccountService;
import utility.Constants;

import java.util.Arrays;
import java.util.Objects;

public class BankAccountServiceImpl extends BaseServiceImpl<BankAccountRepositoryImpl, BankAccount> implements BankAccountService {
    public BankAccountServiceImpl(BankAccountRepositoryImpl repository) {
        super(repository);
    }

    @Override
    public BankAccount validateAndSetInformation(BankAccount bankAccount) {
        try{
            printer.printMessage("Select the Account bank");
            printer.printListWithSelect(Arrays.stream(Bank.values()).map(Objects::toString).toList());
            bankAccount.setBank(Bank.values()[input.nextInt()-1]);
            input.nextLine();
            printer.getInput("Account card number");
            bankAccount.setCardNumber(input.next());
            input.nextLine();
            printer.getInput("cvv2");
            bankAccount.setCvv2(input.next());
            input.nextLine();
            printer.getInput("Card expiration month");
            bankAccount.setExpirationMonth(input.nextInt());
            input.nextLine();
            printer.getInput("Card expiration year");
            bankAccount.setExpirationYear(input.nextInt());
            input.nextLine();
            printer.getInput("Initial balance");
            bankAccount.setBalance(input.nextDouble());
            input.nextLine();
            return bankAccount;
        } catch (Exception e){
            printer.printError(e.getMessage());
            return null;
        }
    }

    @Override
    public BankAccount findByCardNumber(String cardNumber) throws NotFoundException {
        try{
            return repository.findByCardNumber(cardNumber).orElseThrow(()-> new NotFoundException(Constants.NO_BANKACCOUNT_FOUND));
        } catch (Exception e){
            throw new NotFoundException(Constants.NO_BANKACCOUNT_FOUND);
        }

    }
}
