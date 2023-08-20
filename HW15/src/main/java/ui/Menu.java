package ui;

import entity.*;
import exceptions.NotFoundException;
import exceptions.NotSavedException;
import service.impl.*;
import utility.ApplicationContext;
import utility.Constants;
import utility.Printer;

import java.sql.SQLException;
import java.util.*;
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


    public Menu() {
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

    public void begin() {

        while (true) {
            printer.printMenu(Constants.LOGIN_MENU);
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> login();
                case 2 -> {
                    return;
                }
                default -> printer.printError("Wrong entry!");
            }
        }
    }

    private void login() {
        printer.getInput("Username ");
        String username = input.next();
        printer.getInput("Password ");
        String password = input.next();
        try {
            user = personService.findUser(username, password);
            if (user instanceof Employee)
                showEmployeeMenu();
            else if (user instanceof Teacher) {
//                showTeacherMenu();
            } else if (user instanceof Student) {
//                showStudentMenu();
            }
        } catch (NotFoundException e) {
            printer.printError(e.getMessage());
        }
    }

    private void showEmployeeMenu() {
        while (true) {
            printer.printMenu(Constants.EMPLOYEE_MAIN_MENU);
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> showEmployeeStudentsMenu();
                case 2 -> showEmployeeTeacherMenu();
                case 3 -> showEmployeeEmployeeMenu();
                case 4 -> showEmployeeCourseMenu();
                case 5 -> getEmployeeSalaryReport((Employee) user);
                case 6 -> {
                    return;
                }
                default -> printer.printError("Wrong entry!");
            }
        }
    }

    private void getEmployeeSalaryReport(Employee employee) {
        printer.printResult("EMPLOYEE SALARY RESULT", List.of(employeeService.getSalaryReport(employee).toString()));
    }

    ////////////////////////////////////EMPLOYEE_STUDENT_MENU/////////////////////////////////////////
    private void showEmployeeStudentsMenu() {
        while (true) {
            printer.printMenu(Constants.EMPLOYEE_STUDENT_MENU);
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> registerStudent();
                case 2 -> updateStudentInformation();
                case 3 -> removeStudent();
                case 4 -> showAllStudents();
                case 5 -> {
                    return;
                }
                default -> printer.printError("Wrong entry!");
            }
        }
    }


    private void showAllStudents() {
        List<String> list = studentService.findAll().stream().map(Objects::toString).toList();
        printer.printResult("ALL STUDENTS LIST", list);
    }

    private void removeStudent() {
        printer.getInput("Student id");
        long id = input.nextLong();
        input.nextLine();
        try {
            Student student = studentService.findById(id);
            studentService.delete(student);
        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }

    private void updateStudentInformation() {
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
            if (student != null)
                printer.printResult("STUDENT INFORMATION UPDATED", List.of(student.toString()));
        } catch (NotFoundException e) {
            printer.printError(e.getMessage());
        }
    }

    private void registerStudent() {
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
        Student student = new Student(firstname, lastname, nationalCode, phoneNumber, username, password,
                emailAddress, studentCode, entranceSemesterNumber);
        student = studentService.saveOrUpdate(student);
        if (student != null)
            printer.printResult("STUDENT REGISTERED", List.of(student.toString()));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////EMPLOYEE_TEACHER_MENU/////////////////////////////////////////
    private void showEmployeeTeacherMenu() {
        while (true) {
            printer.printMenu(Constants.EMPLOYEE_TEACHER_MENU);
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> addTeacher();
                case 2 -> updateTeacherInformation();
                case 3 -> removeTeacher();
                case 4 -> showAllTeachers();
                case 5 -> {
                    return;
                }
                default -> printer.printError("Wrong entry!");
            }
        }
    }


    private void showAllTeachers() {
        List<String> list = teacherService.findAll().stream().map(Objects::toString).toList();
        printer.printResult("ALL TEACHERS LIST", list);
    }

    private void removeTeacher() {
        printer.getInput("Teacher id");
        long id = input.nextLong();
        input.nextLine();
        try {
            Teacher teacher = teacherService.findById(id);
            teacherService.delete(teacher);
        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }

    private void updateTeacherInformation() {
        printer.getInput("Teacher id");
        long id = input.nextLong();
        input.nextLine();
        try {
            Teacher teacher = teacherService.findById(id);
            printer.getInput("Teacher new name");
            String firstname = input.nextLine();
            printer.getInput("Teacher new lastname");
            String lastname = input.nextLine();
            printer.getInput("Teacher new National code");
            String nationalCode = input.next();
            input.nextLine();
            printer.getInput("Teacher new phone number");
            String phoneNumber = input.next();
            input.nextLine();
            printer.getInput("Teacher new email address");
            String emailAddress = input.next();
            input.nextLine();
            printer.getInput("Teacher new username");
            String username = input.next();
            input.nextLine();
            printer.getInput("Teacher new password");
            String password = input.next();
            input.nextLine();
            printer.getInput("Teacher new unique code");
            String teacherCode = input.next();
            input.nextLine();
            printer.getInput("new TeacherType (1: FACULTY_MEMBER , 2: ADJUNCT_PROFESSOR");
            TeacherType teacherType = TeacherType.valueOf(input.nextInt());
            input.nextLine();
            printer.getInput("Teacher new fixed salary (not important for an ADJUNCT_PROFESSOR)");
            long fixedSalary = input.nextLong();
            input.nextLine();
            printer.getInput("Teacher new per credit salary");
            long perCreditSalary = input.nextLong();
            input.nextLine();

            Set<Course> presentedCourses = teacher.getPresentedCourses();
            SalaryReport salaryReport = teacher.getSalaryReport();
            teacher = Teacher.builder().id(id).firstname(firstname).lastname(lastname).nationalCode(nationalCode)
                    .phoneNumber(phoneNumber).username(username).password(password).email(emailAddress)
                    .teacherCode(teacherCode).teacherType(teacherType)
                    .fixedSalary(fixedSalary).perCreditSalary(perCreditSalary)
                    .presentedCourses(presentedCourses).salaryReport(salaryReport).build();
            teacher.calculateTotalSalary();
            salaryReport.setSalaryAmount(teacher.getTotalSalary());
            teacherService.saveOrUpdate(teacher, teacher.getPresentedCourses().stream().toList(), teacher.getSalaryReport());
            if (teacher != null)
                printer.printResult("TEACHER INFORMATION UPDATED", List.of(teacher.toString()));
        } catch (NotFoundException e) {
            printer.printError(e.getMessage());
        }
    }

    private void addTeacher() {
        printer.getInput("Teacher name");
        String firstname = input.nextLine();
        printer.getInput("Teacher lastname");
        String lastname = input.nextLine();
        printer.getInput("Teacher National code");
        String nationalCode = input.next();
        input.nextLine();
        printer.getInput("Teacher phone number");
        String phoneNumber = input.next();
        input.nextLine();
        printer.getInput("Teacher email address");
        String emailAddress = input.next();
        input.nextLine();
        printer.getInput("Teacher username");
        String username = input.next();
        input.nextLine();
        printer.getInput("Teacher password");
        String password = input.next();
        input.nextLine();
        printer.getInput("Teacher unique code");
        String teacherCode = input.next();
        input.nextLine();
        printer.getInput("TeacherType (1: FACULTY_MEMBER , 2: ADJUNCT_PROFESSOR");
        TeacherType teacherType = TeacherType.valueOf(input.nextInt());
        input.nextLine();
        printer.getInput("Teacher fixed salary (not important for an ADJUNCT_PROFESSOR)");
        long fixedSalary = input.nextLong();
        input.nextLine();
        printer.getInput("Teacher per credit salary");
        long perCreditSalary = input.nextLong();
        input.nextLine();
        Teacher teacher = new Teacher(firstname, lastname, nationalCode, phoneNumber, emailAddress, username,
                password, teacherCode, teacherType, fixedSalary, perCreditSalary);
        teacher = teacherService.saveOrUpdate(teacher, teacher.getPresentedCourses().stream().toList(), teacher.getSalaryReport());
        if (teacher != null)
            printer.printResult("TEACHER REGISTERED", List.of(teacher.toString()));
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////EMPLOYEE_EMPLOYEE_MENU/////////////////////////////////////////
private void showEmployeeEmployeeMenu() {
    while (true) {
        printer.printMenu(Constants.EMPLOYEE_EMPLOYEE_MENU);
        int choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1 -> addEmployee();
            case 2 -> updateEmployeeInformation();
            case 3 -> removeEmployee();
            case 4 -> showAllEmployees();
            case 5 -> {
                return;
            }
            default -> printer.printError("Wrong entry!");
        }
    }
}


    private void showAllEmployees() {
        List<String> list = employeeService.findAll().stream().map(Objects::toString).toList();
        printer.printResult("ALL EMPLOYEES LIST", list);
    }

    private void removeEmployee() {
        printer.getInput("Employee id");
        long id = input.nextLong();
        input.nextLine();
        try {
            Employee employee = employeeService.findById(id);
            employeeService.delete(employee);
        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }

    private void updateEmployeeInformation() {
        printer.getInput("Employee id");
        long id = input.nextLong();
        input.nextLine();
        try {
            Employee employee = employeeService.findById(id);
            printer.getInput("Employee new name");
            String firstname = input.nextLine();
            printer.getInput("Employee new lastname");
            String lastname = input.nextLine();
            printer.getInput("Employee new National code");
            String nationalCode = input.next();
            input.nextLine();
            printer.getInput("Employee new phone number");
            String phoneNumber = input.next();
            input.nextLine();
            printer.getInput("Employee new username");
            String username = input.next();
            input.nextLine();
            printer.getInput("Employee new password");
            String password = input.next();
            input.nextLine();
            printer.getInput("Employee new email address");
            String emailAddress = input.next();
            input.nextLine();
            printer.getInput("Employee new unique code");
            String employeeCode = input.next();
            input.nextLine();
            printer.getInput("Employee new salary");
            long salary = input.nextLong();
            input.nextLine();

            SalaryReport salaryReport = employee.getSalaryReport();
            employee = Employee.builder().id(id).firstname(firstname).lastname(lastname).nationalCode(nationalCode)
                    .phoneNumber(phoneNumber).username(username).password(password).email(emailAddress)
                    .employeeCode(employeeCode)
                    .totalSalary(salary)
                    .salaryReport(salaryReport).build();
            salaryReport.setSalaryAmount(employee.getTotalSalary());
            employeeService.saveOrUpdate(employee,employee.getSalaryReport());
            if (employee != null)
                printer.printResult("EMPLOYEE INFORMATION UPDATED", List.of(employee.toString()));
        } catch (NotFoundException e) {
            printer.printError(e.getMessage());
        }
    }

    private void addEmployee() {
        printer.getInput("Employee name");
        String firstname = input.nextLine();
        printer.getInput("Employee lastname");
        String lastname = input.nextLine();
        printer.getInput("Employee National code");
        String nationalCode = input.next();
        input.nextLine();
        printer.getInput("Employee phone number");
        String phoneNumber = input.next();
        input.nextLine();
        printer.getInput("Employee username");
        String username = input.next();
        input.nextLine();
        printer.getInput("Employee password");
        String password = input.next();
        input.nextLine();
        printer.getInput("Employee email address");
        String emailAddress = input.next();
        input.nextLine();
        printer.getInput("Employee unique code");
        String employeeCode = input.next();
        input.nextLine();
        printer.getInput("Employee salary");
        long salary = input.nextLong();
        input.nextLine();

        Employee employee = new Employee(firstname,lastname,nationalCode,phoneNumber,username,password,
                emailAddress,employeeCode,salary);
        employee = employeeService.saveOrUpdate(employee,employee.getSalaryReport());
        if (employee != null)
            printer.printResult("EMPLOYEE REGISTERED", List.of(employee.toString()));
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////EMPLOYEE_COURSE_MENU/////////////////////////////////////////
private void showEmployeeCourseMenu() {
    while (true) {
        printer.printMenu(Constants.EMPLOYEE_COURSE_MENU);
        int choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1 -> addCourse();
            case 2 -> updateCourseInformation();
            case 3 -> removeCourse();
            case 4 -> showAllCourses();
            case 5 -> {
                return;
            }
            default -> printer.printError("Wrong entry!");
        }
    }
}


    private void showAllCourses() {
        List<String> list = courseService.findAll().stream().map(Objects::toString).toList();
        printer.printResult("ALL COURSES LIST", list);
    }

    private void removeCourse() {
        printer.getInput("Course id");
        long id = input.nextLong();
        input.nextLine();
        try {
            Course course = courseService.findById(id);
            courseService.delete(course);
        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }

    private void updateCourseInformation() {
        printer.getInput("Course id");
        long id = input.nextLong();
        input.nextLine();
        try {
            courseService.findById(id);
            printer.getInput("Course new title");
            String title = input.nextLine();
            printer.getInput("Course new credits");
            int credits = input.nextInt();
            input.nextLine();
            printer.getInput("Course semester new number");
            int semesterNumber = input.nextInt();
            input.nextLine();

            Course course = Course.builder().id(id).title(title).credits(credits).semesterNumber(semesterNumber).build();
            courseService.saveOrUpdate(course);
            if (course != null)
                printer.printResult("COURSE INFORMATION UPDATED", List.of(course.toString()));
        } catch (NotFoundException e) {
            printer.printError(e.getMessage());
        }
    }

    private void addCourse() {
        printer.getInput("Course new title");
        String title = input.nextLine();
        printer.getInput("Course new credits");
        int credits = input.nextInt();
        input.nextLine();
        printer.getInput("Course semester new number");
        int semesterNumber = input.nextInt();
        input.nextLine();

        Course course = new Course(title,credits,semesterNumber);
        course = courseService.saveOrUpdate(course);
        if (course != null)
            printer.printResult("COURSE REGISTERED", List.of(course.toString()));
    }
}
