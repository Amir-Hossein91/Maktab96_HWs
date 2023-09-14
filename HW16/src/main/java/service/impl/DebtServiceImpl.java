package service.impl;

import basics.service.impl.BaseServiceImpl;
import com.github.mfathi91.time.PersianDate;
import entity.BankAccount;
import entity.Debt;
import entity.Loan;
import entity.Student;
import exceptions.NotFoundException;
import repository.impl.DebtRepositoryImpl;
import service.DebtService;
import utility.ApplicationContext;
import utility.Constants;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class DebtServiceImpl extends BaseServiceImpl<DebtRepositoryImpl, Debt> implements DebtService {

    private final BankAccountServiceImpl bankAccountService;

    public DebtServiceImpl(DebtRepositoryImpl repository) {
        super(repository);
        bankAccountService = ApplicationContext.bankAccountService;
    }

    @Override
    public List<Debt> calculateDebts(Loan loan) {
        List<Debt> debts = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            if (i != 5) {
                for (int j = 1; j <= 12; j++) {
                    Debt debt = new Debt(loan);
                    // 0.00279 = (amount * 1.04) / 372
                    debt.setAmount(Math.pow(2, i - 1) * 0.00279 * loan.getAmount());
                    int monthNumber = 12 * (i - 1) + j;
                    debt.setDueDate(calculateDueDate(loan, monthNumber));
                    debts.add(debt);
                }
            } else {
                for (int j = 1; j <= 11; j++) {
                    Debt debt = new Debt(loan);
                    debt.setAmount(Math.pow(2, i - 1) * 0.00279 * loan.getAmount());
                    int monthNumber = 12 * (i - 1) + j;
                    debt.setDueDate(calculateDueDate(loan, monthNumber));
                    debts.add(debt);
                }
                Debt finalDebt = new Debt(loan);
                finalDebt.setAmount(1.04 * loan.getAmount() - debts.stream().map(Debt::getAmount).reduce(0d, Double::sum));
                finalDebt.setDueDate(calculateDueDate(loan, 60));
                debts.add(finalDebt);
            }
        }
        return debts;
    }

    @Override
    public List<Debt> getPaidDebts(Student student) {
        try {
            return repository.getPaidDebts(student).orElseThrow(() -> new NotFoundException(Constants.NO_PAID_DEBTS));
        } catch (Exception e) {
            printer.printError(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Debt> getUnpaidDebts(Student student) {
        try {
            return repository.getUnpaidDebts(student).orElseThrow(() -> new NotFoundException(Constants.NO_UNPAID_DEBTS));
        } catch (Exception e) {
            printer.printError(e.getMessage());
            return null;
        }
    }


    @Override
    public List<Debt> getMonthlyUnpaidDebts(Student student) {
        printer.printMessage("Specify the year and month of debts");
        printer.getInput("Year");
        int year = input.nextInt();
        input.nextLine();
        printer.getInput("Month");
        int month = input.nextInt();
        try {
            return repository.getMonthlyUnpaidDebts(student, year, month).orElseThrow(() -> new NotFoundException(Constants.NO_SPECIFIED_MONTH_UNPAID_DEBTS));
        } catch (Exception e) {
            printer.printError(e.getMessage());
            return null;
        }
    }

    @Override
    public void payDebt(Student student) {
        List<String> result = new ArrayList<>();
        getUnpaidDebts(student).forEach(debt ->
                result.add(debt.getId() + "- " + PersianDate.fromGregorian(debt.getDueDate())
                        + "\t" + debt.getLoan().getLoanType() + "\t" + debt.getAmount()));
        if (!result.isEmpty()) {
            printer.printMessage("Enter bank account information");
            printer.getInput("Card number");
            String cardNumber = input.next();
            input.nextLine();
            printer.getInput("CVV2");
            String cvv2 = input.next();
            input.nextLine();
            printer.getInput("Expiration month");
            int month = input.nextInt();
            input.nextLine();
            printer.getInput("Expiration year");
            int year = input.nextInt();
            input.nextLine();
            try {
                BankAccount bankAccount = bankAccountService.findByCardNumber(cardNumber);
                if (!bankAccount.getCvv2().equals(cvv2) || bankAccount.getExpirationMonth() != month || bankAccount.getExpirationYear() != year)
                    throw new NotFoundException(Constants.INVALID_CARD_PROPERTIES);
                printer.printResult("Choose debt id", result);
                printer.getInput("Debt id");
                long debtId = input.nextLong();
                input.nextLine();
                Debt debt = findById(debtId);
                if (debt != null) {
                    bankAccount.setBalance(bankAccount.getBalance() - debt.getAmount());
                    debt.setPaid(true);
                    debt.setPaidDate(ApplicationContext.currentDate);
                    bankAccountService.saveOrUpdate(bankAccount);
                    saveOrUpdate(debt);
                }
            } catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
                printer.printError(e.getMessage());
            }
        } else
            printer.printError("No Unpaid debts found!");
    }

    @Override
    public List<Debt> findByLoanId(Long loanId) {
        try {
            return repository.findByLoanId(loanId).orElseThrow(() -> new NotFoundException(Constants.INVALID_LOAN_ID));
        } catch (Exception e) {
            printer.printError(e.getMessage());
            return null;
        }
    }

    private LocalDate calculateDueDate(Loan loan, int monthNumber) {
        int year = loan.getBorrower().getGraduateYear() + 1;
        LocalDate date = loan.getRegistrationDate();
        PersianDate persianDate = PersianDate.fromGregorian(date);
        int day = persianDate.getDayOfMonth();
        year += monthNumber / 12;
        int month;
        if (monthNumber % 12 > 0)
            month = monthNumber % 12;
        else {
            month = 12;
            year -= 1;
        }
        return PersianDate.of(year, month, day).toGregorian();
    }
}
