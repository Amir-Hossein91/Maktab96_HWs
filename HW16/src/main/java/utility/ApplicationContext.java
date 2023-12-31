package utility;

import com.github.mfathi91.time.PersianDate;
import entity.BankAccount;
import entity.Debt;
import entity.Loan;
import entity.Student;
import repository.impl.BankAccountRepositoryImpl;
import repository.impl.DebtRepositoryImpl;
import repository.impl.LoanRepositoryImpl;
import repository.impl.StudentRepositoryImpl;
import service.impl.BankAccountServiceImpl;
import service.impl.DebtServiceImpl;
import service.impl.LoanServiceImpl;
import service.impl.StudentServiceImpl;

import java.time.LocalDate;
import java.util.*;

public class ApplicationContext {
    public final static PersianDate currentPersianDate;
    public final static LocalDate currentDate ;
    public final static Printer printer;
    private final static BankAccountRepositoryImpl bankAccountRepository;
    private final static StudentRepositoryImpl studentRepository;
    private final static LoanRepositoryImpl loanRepository;
    public final static DebtRepositoryImpl debtRepository;
    public final static BankAccountServiceImpl bankAccountService;
    public final static StudentServiceImpl studentService;
    public final static LoanServiceImpl loanService;
    public final static DebtServiceImpl debtService;
    public final static Map<Integer,Character[]> passwordCharactersMap;

    static{
        currentPersianDate = PersianDate.of(1395,8,3);
        currentDate = currentPersianDate.toGregorian();
        printer = new Printer();
        bankAccountRepository = new BankAccountRepositoryImpl(BankAccount.class);
        studentRepository = new StudentRepositoryImpl(Student.class);
        loanRepository = new LoanRepositoryImpl(Loan.class);
        debtRepository = new DebtRepositoryImpl(Debt.class);
        bankAccountService = new BankAccountServiceImpl(bankAccountRepository);
        studentService = new StudentServiceImpl(studentRepository);
        loanService = new LoanServiceImpl(loanRepository);
        debtService = new DebtServiceImpl(debtRepository);
        passwordCharactersMap = new HashMap<>(){{
           put(1,Constants.UPPER_CASE_CHARACTERS);
           put(2,Constants.LOWER_CASE_CHARACTERS);
           put(3,Constants.DIGIT_CHARACTERS);
           put(4,Constants.SPECIAL_CHARACTERS);
        }};
    }
}
