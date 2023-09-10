package service.impl;

import entity.Loan;
import entity.Student;
import entity.enums.LoanType;
import org.junit.jupiter.api.Test;
import utility.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class DebtServiceImplTest {
    DebtServiceImpl debtService = ApplicationContext.debtService;

    @Test
    void debtsListSizeEqualsToSixty(){

        Student student = Student.builder().entranceYear(2020).build();
        Loan loan = Loan.builder()
                .loanType(LoanType.EDUCATIONAL)
                .amount(3200000L).borrower(student)
                .registrationDate(ApplicationContext.currentDate)
                .build();
        assertEquals(60,debtService.calculateDebts(loan).size());
    }

    @Test
    void paidDebtsOfUnregisteredIsEmpty(){
        Student student = Student.builder().id(0).build();
        assertTrue(debtService.getPaidDebts(student).isEmpty());
    }

    @Test
    void unPaidDebtsOfUnregisteredIsEmpty(){
        Student student = Student.builder().id(0).build();
        assertTrue(debtService.getUnpaidDebts(student).isEmpty());
    }

    @Test
    void debtsListOfUnSavedLoanIsEmpty(){
        Loan loan = Loan.builder().id(0).build();
        assertTrue(debtService.findByLoanId(loan.getId()).isEmpty());
    }

}