package service.impl;

import entity.Loan;
import entity.Student;
import entity.enums.LoanType;
import org.junit.jupiter.api.Test;
import utility.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class LoanServiceImplTest {

    LoanServiceImpl loanService = ApplicationContext.loanService;

    @Test
    void savingLoan(){
        Student student = Student.builder().id(1).build();
        Loan loan = Loan.builder().amount(2000000L).borrower(student).registrationDate(ApplicationContext.currentDate).build();
        assertNotNull(loanService.saveOrUpdate(loan));
    }

    @Test
    void choosingALoanSetsARegistrationDateForIt(){
        Loan loan = Loan.builder().loanType(LoanType.TUITION).build();
        assertNotNull(loanService.chooseLoan(loan).getRegistrationDate());
    }

    @Test
    void possibleLoansIfNotTakenWillBeInPossibleLoansList(){
        Student student = Student.builder().id(2).build();
        assertFalse(loanService.getPossibleLoans(student).isEmpty());
    }

}