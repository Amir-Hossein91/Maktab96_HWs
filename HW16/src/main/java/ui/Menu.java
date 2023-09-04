package ui;

import entity.Student;
import exceptions.InvalidDateException;
import exceptions.NotSavedException;
import service.impl.BankAccountServiceImpl;
import service.impl.DebtServiceImpl;
import service.impl.LoanServiceImpl;
import service.impl.StudentServiceImpl;
import utility.ApplicationContext;
import utility.Constants;
import utility.Printer;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

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
                        studentService.saveOrUpdate(student);
                    }
                    case 2 -> showLoginMenu();
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

    public void showLoginMenu() {

    }
}
