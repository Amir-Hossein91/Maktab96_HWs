package ui;

import entity.*;
import exceptions.NotFoundException;
import exceptions.NotSavedException;
import service.impl.*;
import utility.ApplicationContext;
import utility.Constants;
import utility.Printer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {

    private final CourseServiceImpl courseService;
    private final EmployeeServiceImpl employeeService;
    private final PersonServiceImpl personService;
    private final SalaryReportServiceImpl salaryReportService;
    private final ScoreServiceImpl scoreService;
    private final StudentServiceImpl studentService;
    private final TeacherServiceImpl teacherService;

    private Printer printer;
    private Scanner input;

    private Person user;


    public Menu (){
        courseService = ApplicationContext.courseService;
        employeeService = ApplicationContext.employeeService;
        personService = ApplicationContext.personService;
        salaryReportService = ApplicationContext.salaryReportService;
        scoreService = ApplicationContext.scoreService;
        studentService = ApplicationContext.studentService;
        teacherService = ApplicationContext.teacherService;

        printer = ApplicationContext.printer;
        input = ApplicationContext.scanner;
    }

    public void begin(){

        while (true){
            printer.printMenu(Constants.LOGIN_MENU);
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> login();
                case 2 -> {return;}
                default -> printer.printError("Wrong entry!");
            }
        }
    }
    private void login(){
        printer.getInput("Username ");
        String username = input.next();
        printer.getInput("Password ");
        String password = input.next();
        try {
           user = personService.findUser(username, password);
            if(user instanceof Employee)
                showEmployeeMenu();
            else if (user instanceof Teacher) {
//                showTeacherMenu();
            }
            else if (user instanceof Student) {
//                showStudentMenu();
            }
        } catch (NotFoundException e) {
            printer.printError(e.getMessage());
        }
    }

    private void showEmployeeMenu(){
        while(true){
            printer.printMenu(Constants.EMPLOYEE_MAIN_MENU);
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> showEmployeeStudentsMenu();
//                case 2 -> showEmployeeTeacherMenu();
//                case 3 -> showEmployeeEmployeeMenu();
//                case 4 -> showEmployeeCoursMenu();
                case 5 -> getEmployeeSalaryReport((Employee) user);
                case 6 -> {return;}
                default -> printer.printError("Wrong entry!");
            }
        }
    }

    private void getEmployeeSalaryReport(Employee employee){
        printer.printResult("EMPLOYEE SALARY RESULT", List.of(employeeService.getSalaryReport(employee).toString()));
    }

    private void showEmployeeStudentsMenu(){
        while(true){
            printer.printMenu(Constants.EMPLOYEE_STUDENT_MENU);
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> registerStudent();
                case 2 -> updateStudentInformation();
                case 3 -> removeStudent();
                case 4 -> showAllStudents();
                case 5 -> {return;}
                default -> printer.printError("Wrong entry!");
            }
        }
    }


    private void showAllStudents(){
        List<String> list = studentService.findAll().stream().map(Objects::toString).toList();
        printer.printResult("ALL STUDENTS LIST",list);
    }

    private void removeStudent(){
        printer.getInput("Student id");
        long id = input.nextLong();
        input.nextLine();
        try {
            Student student = studentService.findById(id);
            studentService.delete(student);
        } catch (Exception e){
            printer.printError(e.getMessage());
        }
    }

    private void updateStudentInformation(){
        printer.getInput("Student id");
        long id = input.nextLong();
        input.nextLine();
        try {
            studentService.findById(id);
            printer.getInput("Student new name");
            String firstname = input.nextLine();
            printer.getInput("Student new lastname");
            String lastname = input.nextLine();
            printer.getInput("Student new National code");
            String nationalCode = input.next();
            input.nextLine();
            printer.getInput("Student new phone number");
            String phoneNumber = input.next();
            input.nextLine();
            printer.getInput("Student new username");
            String username = input.next();
            input.nextLine();
            printer.getInput("Student new password");
            String password = input.next();
            input.nextLine();
            printer.getInput("Student new email address");
            String emailAddress = input.next();
            input.nextLine();
            printer.getInput("Student new unique code");
            String studentCode = input.next();
            input.nextLine();
            printer.getInput("Student new entrance semester number");
            int entranceSemesterNumber = input.nextInt();
            input.nextLine();
            Student student = Student.builder().id(id).firstname(firstname).lastname(lastname).nationalCode(nationalCode)
                    .phoneNumber(phoneNumber).username(username).password(password).email(emailAddress)
                    .studentCode(studentCode).entranceSemesterNumber(entranceSemesterNumber).build();
            student = studentService.saveOrUpdate(student);
            if(student!=null)
                printer.printResult("STUDENT INFORMATION UPDATED",List.of(student.toString()));
        } catch (NotFoundException e) {
            printer.printError(e.getMessage());
        }
    }

    private void registerStudent(){
        printer.getInput("Student name");
        String firstname = input.nextLine();
        printer.getInput("Student lastname");
        String lastname = input.nextLine();
        printer.getInput("Student National code");
        String nationalCode = input.next();
        input.nextLine();
        printer.getInput("Student phone number");
        String phoneNumber = input.next();
        input.nextLine();
        printer.getInput("Student username");
        String username = input.next();
        input.nextLine();
        printer.getInput("Student password");
        String password = input.next();
        input.nextLine();
        printer.getInput("Student email address");
        String emailAddress = input.next();
        input.nextLine();
        printer.getInput("Student unique code");
        String studentCode = input.next();
        input.nextLine();
        printer.getInput("Student entrance semester number");
        int entranceSemesterNumber = input.nextInt();
        input.nextLine();
        Student student = new Student(firstname,lastname,nationalCode,phoneNumber,username,password,
                emailAddress,studentCode,entranceSemesterNumber);
        student = studentService.saveOrUpdate(student);
        if(student!=null)
            printer.printResult("STUDENT REGISTERED",List.of(student.toString()));
    }
}
