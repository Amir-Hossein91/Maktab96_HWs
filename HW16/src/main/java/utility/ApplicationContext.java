package utility;

import entity.BankAccount;
import entity.Debt;
import entity.Loan;
import entity.Student;
import repository.BankAccountRepositoryImpl;
import repository.DebtRepositoryImpl;
import repository.LoanRepositoryImpl;
import repository.StudentRepositoryImpl;
import service.impl.BankAccountServiceImpl;
import service.impl.DebtServiceImpl;
import service.impl.LoanServiceImpl;
import service.impl.StudentServiceImpl;

import java.util.*;

public class ApplicationContext {
    public final static GregorianCalendar gregorianCalendar;
    public final static Date currentDate ;
    public final static Printer printer;
    private final static BankAccountRepositoryImpl bankAccountRepository;
    private final static StudentRepositoryImpl studentRepository;
    private final static LoanRepositoryImpl loanRepository;
    private final static DebtRepositoryImpl debtRepository;
    public final static BankAccountServiceImpl bankAccountService;
    public final static StudentServiceImpl studentService;
    public final static LoanServiceImpl loanService;
    public final static DebtServiceImpl debtService;
    public final static Map<Integer,Character[]> passwordCharactersMap;

    static{
        gregorianCalendar = new GregorianCalendar(2020,Calendar.APRIL,19);
        currentDate = gregorianCalendar.getTime();
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
