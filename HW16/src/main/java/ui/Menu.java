package ui;

import entity.Loan;
import entity.Student;
import entity.enums.LoanType;
import service.impl.BankAccountServiceImpl;
import service.impl.DebtServiceImpl;
import service.impl.LoanServiceImpl;
import service.impl.StudentServiceImpl;
import utility.ApplicationContext;
import utility.Constants;
import utility.Printer;

import java.text.SimpleDateFormat;
import java.util.*;

public class Menu {
    private final Printer printer = ApplicationContext.printer;
    private final Scanner input = new Scanner(System.in);
    private final BankAccountServiceImpl bankAccountService = ApplicationContext.bankAccountService;
    private final StudentServiceImpl studentService = ApplicationContext.studentService;
    private final LoanServiceImpl loanService = ApplicationContext.loanService;
    private final DebtServiceImpl debtService = ApplicationContext.debtService;

    public void showRegistryMenu() {

        while (true) {
            try {
                printer.printMenu(Constants.REGISTRY_MENU);
                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1 -> {
                        Student student = new Student();
                        studentService.validateAndSetInformation(student);
                        studentService.setUsernameAndPassword(student);
                        student = studentService.saveOrUpdate(student);
                        studentService.showUsernameAndPassword(student);
                    }
                    case 2 -> {
                        String username = studentService.getUsername();
                        String password = studentService.getPassword();
                        Student student = studentService.checkUsernameAndPassword(username, password);
                        if(student != null)
                            showUserMenu(student);
                    }
                    case 3 -> {
                        return;
                    }
                    default -> printer.printError("Wrong entry!");
                }
            } catch (Exception e) {
                if (e instanceof InputMismatchException)
                    printer.printError("Wrong entry!");
                else
                    printer.printError(e.getMessage());
                input.nextLine();
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }

    public void showUserMenu(Student student) {
        while (true) {
            try {
                printer.printMenu(Constants.STUDENT_LOAN_MENU);
                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1 -> {
                        if(!studentService.isGraduated(student)){
                            if(studentService.isRegistrationOpen()){
                                showLoanRegisterMenu(student);
                            }
                        }
                    }
                    case 2 -> {
                        if(studentService.canRepay(student)){
                            showRepayMenu(student);
                        }
                    }
                    case 3 -> {
                        return;
                    }
                    default -> printer.printError("Wrong entry!");
                }
            } catch (Exception e) {
                if (e instanceof InputMismatchException)
                    printer.printError("Wrong entry!");
                else
                    printer.printError(e.getMessage());
                input.nextLine();
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }

    public void showLoanRegisterMenu(Student student){
        while (true) {
            try {
                printer.printMenu(Constants.LOAN_REGISTER_MENU);

                List<Loan> possibleLoans = loanService.getPossibleLoans(student);
                List<String> toBePrinted = new ArrayList<>();
                possibleLoans.forEach(loan ->
                       toBePrinted.add(loan.getLoanType() + "\t" + loan.getAmount()));

                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1 ->
                        printer.printListWithoutSelect(toBePrinted);
                    case 2 -> {
                        printer.printListWithSelect(toBePrinted);
                        Loan loan = loanService.chooseLoan(possibleLoans.get(input.nextInt()-1));
                        loanService.saveOrUpdate(loan);
                        input.nextLine();
                    }
                    case 3 -> {
                        return;
                    }
                    default -> printer.printError("Wrong entry!");
                }
            } catch (Exception e) {
                if (e instanceof InputMismatchException)
                    printer.printError("Wrong entry!");
                else
                    printer.printError(e.getMessage());
                input.nextLine();
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }

    public void showRepayMenu(Student student){
        while (true) {
            try {
                printer.printMenu(Constants.REPAY_MENU);
                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1 -> {
                        List<String> result = new ArrayList<>();
                        debtService.getPaidDebts(student).forEach(debt -> {
                            result.add(debt.getId() + "- type = " + debt.getLoan().getLoanType() + "\t"
                                    + new SimpleDateFormat("yyyy/MM/dd").format(debt.getPaidDate()));
                        });
                        printer.printResult("Paid Debts",result);
                    }
                    case 2 -> {
                        List<String> result = new ArrayList<>();
                        debtService.getUnpaidDebts(student).forEach(debt ->
                            result.add(debt.getId() + "- " + new SimpleDateFormat("yyyy/MM/dd").format(debt.getDueDate())
                            +"\t" + debt.getLoan().getLoanType() + "\t" + debt.getAmount()));

                        printer.printResult("Unpaid Debts",result);
                    }
                    case 3 -> {
                        List<String> result = new ArrayList<>();
                        debtService.getMonthlyUnpaidDebts(student).forEach(debt -> {
                            result.add(debt.getId() + "- " + new SimpleDateFormat("yyyy/MM/dd").format(debt.getDueDate())
                                    + "\t" + debt.getAmount());
                        });
                        printer.printResult("Specified month unpaid Debts",result);
                    }
                    case 4 -> {
                        debtService.payDebt(student);
                    }
                    case 5 -> {
                        return;
                    }
                    default -> printer.printError("Wrong entry!");
                }
            } catch (Exception e) {
                if (e instanceof InputMismatchException)
                    printer.printError("Wrong entry!");
                else
                    printer.printError(e.getMessage());
                input.nextLine();
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
