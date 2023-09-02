package utility;

import entity.BankAccount;
import entity.Debt;
import entity.Loan;
import entity.Student;
import repository.BankAccountRepositoryImpl;
import repository.DebtRepositoryImpl;
import repository.LoanRepositoryImpl;
import repository.StudentRepositoryImpl;
import service.BankAccountServiceImpl;
import service.DebtServiceImpl;
import service.LoanServiceImpl;
import service.StudentServiceImpl;

import java.util.Date;
import java.util.GregorianCalendar;

public class ApplicationContext {
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

    static{
        currentDate = new GregorianCalendar(2023,02,19).getTime();
        printer = new Printer();
        bankAccountRepository = new BankAccountRepositoryImpl(BankAccount.class);
        studentRepository = new StudentRepositoryImpl(Student.class);
        loanRepository = new LoanRepositoryImpl(Loan.class);
        debtRepository = new DebtRepositoryImpl(Debt.class);
        bankAccountService = new BankAccountServiceImpl(bankAccountRepository);
        studentService = new StudentServiceImpl(studentRepository);
        loanService = new LoanServiceImpl(loanRepository);
        debtService = new DebtServiceImpl(debtRepository);
    }
}
