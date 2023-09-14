package ui;

import com.github.mfathi91.time.PersianDate;
import entity.Debt;
import entity.Loan;
import entity.Student;
import service.impl.DebtServiceImpl;
import service.impl.LoanServiceImpl;
import service.impl.StudentServiceImpl;
import utility.ApplicationContext;
import utility.Constants;
import utility.Printer;

import java.util.*;

public class Menu {
    private final Printer printer = ApplicationContext.printer;
    private final Scanner input = new Scanner(System.in);
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
                    case 1 ->{
                        List<Loan> loans = loanService.getLoansOf(student);
                        List<String> loanStringList = new ArrayList<>();
                        loans.forEach(loan -> loanStringList.add("id: " + loan.getId() + " - type: "
                                + loan.getLoanType() + " - registered in:  "
                                + PersianDate.fromGregorian(loan.getRegistrationDate())
                                + " - amount: " + loan.getAmount()));
                        printer.printResult("Select each of the taken loans to see debts",loanStringList);
                        if(!loans.isEmpty()){
                            printer.getInput("Loan id");
                            Long loanId = input.nextLong();
                            input.nextLine();
                            if (loans.stream().map(Loan::getId).toList().contains(loanId)){
                                List<Debt> debts = debtService.findByLoanId(loanId);
                                List<String> debtsStringList = new ArrayList<>();
                                debts.forEach(debt ->
                                        debtsStringList.add("id: " + debt.getId() + " - due date: " +
                                                PersianDate.fromGregorian(debt.getDueDate()) +
                                                " - amount: " + debt.getAmount()));
                                printer.printResult("Debts of loan by id of " + loanId,debtsStringList);
                            } else
                                printer.printError(Constants.INVALID_LOAN_ID);
                        }

                    }
                    case 2 -> {
                        if(!possibleLoans.isEmpty()){
                            printer.printListWithSelect(toBePrinted);
                            int loanNumber = input.nextInt();
                            if(loanNumber >0 && loanNumber <= possibleLoans.size()) {
                                Loan loan = loanService.chooseLoan(possibleLoans.get(loanNumber - 1));
                                loanService.saveOrUpdate(loan);
                            } else
                                printer.printError(Constants.INVALID_LOAN_ID);
                            input.nextLine();
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
                            result.add(debt.getId() + " - type = " + debt.getLoan().getLoanType() + "\t"
                                    + PersianDate.fromGregorian(debt.getPaidDate()));
                        });
                        printer.printResult("Paid Debts",result);
                    }
                    case 2 -> {
                        List<String> result = new ArrayList<>();
                        debtService.getUnpaidDebts(student).forEach(debt ->
                            result.add(debt.getId() + "- " + PersianDate.fromGregorian(debt.getDueDate())
                            +"\t" + debt.getLoan().getLoanType() + "\t" + debt.getAmount()));

                        printer.printResult("Unpaid Debts",result);
                    }
                    case 3 -> {
                        List<String> result = new ArrayList<>();
                        debtService.getMonthlyUnpaidDebts(student).forEach(debt -> {
                            result.add(debt.getId() + "- " + PersianDate.fromGregorian(debt.getDueDate())
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
            }
        }
    }
}
