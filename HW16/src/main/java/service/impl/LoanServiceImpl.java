package service.impl;

import basics.service.impl.BaseServiceImpl;
import com.github.mfathi91.time.PersianDate;
import entity.BankAccount;
import entity.Debt;
import entity.Loan;
import entity.Student;
import entity.enums.AcceptanceType;
import entity.enums.LoanType;
import entity.enums.UniversityType;
import exceptions.NotSavedException;
import repository.impl.LoanRepositoryImpl;
import service.LoanService;
import utility.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class LoanServiceImpl extends BaseServiceImpl<LoanRepositoryImpl, Loan> implements LoanService {

    private final StudentServiceImpl studentService;
    private final BankAccountServiceImpl bankAccountService;
    private final DebtServiceImpl debtService;

    public LoanServiceImpl(LoanRepositoryImpl repository) {
        super(repository);
        studentService = ApplicationContext.studentService;
        bankAccountService = ApplicationContext.bankAccountService;
        // for a reason which I couldn't understand, the following line ends in a null pointer exception!!
//        debtService = ApplicationContext.debtService;
        debtService = new DebtServiceImpl(ApplicationContext.debtRepository);
    }

    public Loan saveOrUpdate(Loan loan){
        try{
            if(!transaction.isActive()){
                transaction.begin();
                BankAccount bankAccount = studentService.findBankAccount(loan.getBorrower());
                bankAccount.setBalance(bankAccount.getBalance() + loan.getAmount());
                bankAccountService.saveOrUpdate(bankAccount);
                List<Debt> debts = debtService.calculateDebts(loan);
                for (Debt d : debts){
                    debtService.saveOrUpdate(d);
                }
                loan = repository.saveOrUpdate(loan).orElseThrow(()-> new NotSavedException("\nCould not save Loan!"));
                transaction.commit();
            }
            else {
                BankAccount bankAccount = studentService.findBankAccount(loan.getBorrower());
                bankAccount.setBalance(bankAccount.getBalance() + loan.getAmount());
                bankAccountService.saveOrUpdate(bankAccount);
                List<Debt> debts = debtService.calculateDebts(loan);
                for (Debt d : debts){
                    debtService.saveOrUpdate(d);
                }
                loan = repository.saveOrUpdate(loan).orElseThrow(() -> new NotSavedException("\nCould not save Loan!"));
            }
            if(loan != null)
                printer.printMessage("Loan saved successfully!");
            return loan;
        } catch (Exception e){
            if(transaction.isActive())
                transaction.rollback();
            printer.printError(e.getMessage());
            return null;
        }
    }

    @Override
    public Loan chooseLoan(Loan loan) {
        loan.setRegistrationDate(ApplicationContext.currentDate);
        return loan;
    }

    @Override
    public List<Loan> getPossibleLoans(Student student) {
        List<Loan> possibleLoans = new ArrayList<>();
        if(isHouseLoanPossible(student))
            possibleLoans.add(new Loan(LoanType.HOUSE_RENT,student));
        if(isEducationalLoanPossible(student))
            possibleLoans.add(new Loan(LoanType.EDUCATIONAL,student));
        if(isTuitionLoanPossible(student))
            possibleLoans.add(new Loan(LoanType.TUITION,student));
        return possibleLoans;
    }

    private boolean isHouseLoanPossible(Student student){
        if(!student.isMarried() || student.isInDorm())
            return false;
        else {
            Student spouse = studentService.findByNationalCode(student.getSpouseNationalCode());
            if(spouse != null && isLoanTaken(LoanType.HOUSE_RENT,spouse)){
                return false;
            }
            return !isLoanTaken(LoanType.HOUSE_RENT,student);
        }
    }

    private boolean isEducationalLoanPossible (Student student){
        return !isLoanTaken(LoanType.EDUCATIONAL,student);
    }

    private boolean isTuitionLoanPossible(Student student){
        UniversityType universityType = student.getUniversityType();
        AcceptanceType acceptanceType = student.getAcceptanceType();
        if(universityType == UniversityType.STATE && acceptanceType == AcceptanceType.DAY_PROGRAM)
            return false;
        else
            return !isLoanTaken(LoanType.TUITION,student);
    }

    private boolean isLoanTaken(LoanType loanType, Student student){
        List<Loan> loans = getLoansOf(student);
        List<LoanType> loanTypes = loans.stream().map(Loan::getLoanType).toList();
        if(loans.isEmpty() || !loanTypes.contains(loanType)) {
            return false;
        }
        else {
            switch(loanType){
                case TUITION -> {
                    List<Loan> tuitionLoans = loans.stream().filter(loan -> loan.getLoanType() == LoanType.TUITION).toList();
                    return areLoansInCurrentPeriod(tuitionLoans);
                }
                case EDUCATIONAL -> {
                    List<Loan> educationalLoans = loans.stream().filter(loan -> loan.getLoanType() == LoanType.EDUCATIONAL).toList();
                    return areLoansInCurrentPeriod(educationalLoans);
                }
                default -> {
                    return true;
                }
            }
        }
    }

    private boolean areLoansInCurrentPeriod(List<Loan> takenLoans){
        if(!takenLoans.isEmpty()){
            for(Loan l : takenLoans){
                PersianDate persianRegistrationDate = PersianDate.fromGregorian(l.getRegistrationDate());
                int year = persianRegistrationDate.getYear();
                int month = persianRegistrationDate.getMonthValue();
                switch (month){
                    case 8 -> {
                        if(ApplicationContext.currentPersianDate.isAfter(PersianDate.of(year,7,30))
                                && ApplicationContext.currentPersianDate.isBefore(PersianDate.of(year,8,8)))
                            return true;
                    }
                    case 11,12-> {
                        if(ApplicationContext.currentPersianDate.isAfter(PersianDate.of(year,11,24))
                                && ApplicationContext.currentPersianDate.isBefore(PersianDate.of(year,12,2)))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<Loan> getLoansOf(Student student) {
        return repository.getLoansOf(student);
    }




}
