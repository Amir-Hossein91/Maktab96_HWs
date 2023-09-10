package service.impl;

import exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import utility.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountServiceImplTest {

    BankAccountServiceImpl bankAccountService = ApplicationContext.bankAccountService;

    @Test
    void unsavedCardNumberHasNoBankAccount() {
        String cardNumber = "6325415269854785";
        assertThrows(NotFoundException.class,()->bankAccountService.findByCardNumber(cardNumber));
    }

    @Test
    void savedCardNumberHasBankAccount() throws NotFoundException {
        String cardNumber = "6221061073022989";
        assertNotNull(bankAccountService.findByCardNumber(cardNumber));
    }

}