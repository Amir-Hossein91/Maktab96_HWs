package ui;

import entity.*;
import exceptions.NotFoundException;
import service.impl.*;
import utility.ApplicationContext;
import utility.Constants;
import utility.Printer;

import java.util.*;

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
            try{
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
            } catch (Exception e){
                printer.printError(e.getMessage());
                input.nextLine();
            }
        }
    }

    private void login() {
        try {
            printer.getInput("Username ");
            String username = input.next();
            printer.getInput("Password ");
            String password = input.next();
            user = personService.findUser(username, password);
            if (user instanceof Employee)
                showEmployeeMenu();
            else if (user instanceof Teacher) {
                showTeacherMenu();
            } else if (user instanceof Student) {
                showStudentMenu();
            }
        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }

    private void showEmployeeMenu() {
        while (true) {
            try{
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
            } catch (Exception e){
                printer.printError(e.getMessage());
                input.nextLine();
            }

        }
    }

    private void getEmployeeSalaryReport(Employee employee) {
        printer.printResult("EMPLOYEE SALARY REPORT",employeeService.getSalaryReport(employee));
    }

    ////////////////////////////////////EMPLOYEE_STUDENT_MENU/////////////////////////////////////////
    private void showEmployeeStudentsMenu() {
        while (true) {
            try{
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
            } catch (Exception e){
                printer.printError(e.getMessage());
                input.nextLine();
            }

        }
    }


    private void showAllStudents() {
        List<String> list = studentService.findAll().stream().map(Objects::toString).toList();
        printer.printResult("ALL STUDENTS LIST", list);
    }

    private void removeStudent() {
        try {
            printer.getInput("Student id");
            long id = input.nextLong();
            input.nextLine();
            Student student = studentService.findById(id);
            studentService.delete(student);
        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }

    private void updateStudentInformation() {
        try {
            printer.getInput("Student id");
            long id = input.nextLong();
            input.nextLine();
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
                printer.printResult("STUDENT INFORMATION UPDATED",student.toString());
        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }

    private void registerStudent() {
        try {
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
                printer.printResult("STUDENT REGISTERED", student.toString());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////EMPLOYEE_TEACHER_MENU/////////////////////////////////////////
    private void showEmployeeTeacherMenu() {
        while (true) {
            try{
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
            } catch (Exception e){
                printer.printError(e.getMessage());
                input.nextLine();
            }
        }
    }


    private void showAllTeachers() {
        List<String> list = teacherService.findAll().stream().map(Objects::toString).toList();
        printer.printResult("ALL TEACHERS LIST", list);
    }

    private void removeTeacher() {
        try {
            printer.getInput("Teacher id");
            long id = input.nextLong();
            input.nextLine();
            Teacher teacher = teacherService.findById(id);
            teacherService.delete(teacher);
        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }

    private void updateTeacherInformation() {
        try {
            printer.getInput("Teacher id");
            long id = input.nextLong();
            input.nextLine();
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
                printer.printResult("TEACHER INFORMATION UPDATED", teacher.toString());
        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }

    private void addTeacher() {
        try{
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
                printer.printResult("TEACHER REGISTERED", teacher.toString());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////EMPLOYEE_EMPLOYEE_MENU/////////////////////////////////////////
    private void showEmployeeEmployeeMenu() {
        while (true) {
            try{
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
            } catch (Exception e){
                printer.printError(e.getMessage());
                input.nextLine();
            }
        }
    }


    private void showAllEmployees() {
        List<String> list = employeeService.findAll().stream().map(Objects::toString).toList();
        printer.printResult("ALL EMPLOYEES LIST", list);
    }

    private void removeEmployee() {
        try {
            printer.getInput("Employee id");
            long id = input.nextLong();
            input.nextLine();
            Employee employee = employeeService.findById(id);
            employeeService.delete(employee);
        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }

    private void updateEmployeeInformation() {
        try {
            printer.getInput("Employee id");
            long id = input.nextLong();
            input.nextLine();
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
            employeeService.saveOrUpdate(employee, employee.getSalaryReport());
            if (employee != null)
                printer.printResult("EMPLOYEE INFORMATION UPDATED", employee.toString());
        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }

    private void addEmployee() {
        try{
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

            Employee employee = new Employee(firstname, lastname, nationalCode, phoneNumber, username, password,
                    emailAddress, employeeCode, salary);
            employee = employeeService.saveOrUpdate(employee, employee.getSalaryReport());
            if (employee != null)
                printer.printResult("EMPLOYEE REGISTERED", employee.toString());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////EMPLOYEE_COURSE_MENU/////////////////////////////////////////
    private void showEmployeeCourseMenu() {
        while (true) {
            try{
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
            } catch (Exception e){
                printer.printError(e.getMessage());
                input.nextLine();
            }
        }
    }


    private void showAllCourses() {
        List<String> list = courseService.findAll().stream().map(Objects::toString).toList();
        printer.printResult("ALL COURSES LIST", list);
    }

    private void removeCourse() {
        try {
            printer.getInput("Course id");
            long id = input.nextLong();
            input.nextLine();
            Course course = courseService.findById(id);
            courseService.delete(course);
        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }

    private void updateCourseInformation() {
        try {
            printer.getInput("Course id");
            long id = input.nextLong();
            input.nextLine();
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
                printer.printResult("COURSE INFORMATION UPDATED", course.toString());
        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }

    private void addCourse() {
        try{
            printer.getInput("Course title");
            String title = input.nextLine();
            printer.getInput("Course credits");
            int credits = input.nextInt();
            input.nextLine();
            printer.getInput("Course semester number");
            int semesterNumber = input.nextInt();
            input.nextLine();

            Course course = new Course(title, credits, semesterNumber);
            course = courseService.saveOrUpdate(course);
            if (course != null)
                printer.printResult("COURSE REGISTERED", course.toString());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void showTeacherMenu() {
        while (true) {
            try{
                printer.printMenu(Constants.TEACHER_MAIN_MENU);
                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1 -> showTeacherInfoMenu();
                    case 2 -> showTeacherCourseMenu();
                    case 3 -> setScores((Teacher) user);
                    case 4 -> getTeacherSalaryReport((Teacher) user);
                    case 5 -> {
                        return;
                    }
                    default -> printer.printError("Wrong entry!");
                }
            } catch (Exception e){
                printer.printError(e.getMessage());
                input.nextLine();
            }
        }
    }

    private void showTeacherInfoMenu() {
        while (true) {
            try{
                printer.printMenu(Constants.TEACHER_ACCOUNT_MENU);
                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1 -> showTeacherAccount();
                    case 2 -> updateTeacherAccount();
                    case 3 -> {
                        return;
                    }
                    default -> printer.printError("Wrong entry!");
                }
            } catch (Exception e){
                printer.printError(e.getMessage());
                input.nextLine();
            }
        }
    }

    private void showTeacherAccount() {
        try {
            Teacher teacher = teacherService.findById(user.getId());
            printer.printResult("YOUR ACCOUNT INFO",teacher.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateTeacherAccount() {
        try {
            Teacher teacher = teacherService.findById(user.getId());
            printer.getInput("new name");
            String firstname = input.nextLine();
            printer.getInput("new lastname");
            String lastname = input.nextLine();
            printer.getInput("new National code");
            String nationalCode = input.next();
            input.nextLine();
            printer.getInput("new phone number");
            String phoneNumber = input.next();
            input.nextLine();
            printer.getInput("new email address");
            String emailAddress = input.next();
            input.nextLine();
            printer.getInput("new username");
            String username = input.next();
            input.nextLine();
            printer.getInput("new password");
            String password = input.next();

            Set<Course> presentedCourses = teacher.getPresentedCourses();
            SalaryReport salaryReport = teacher.getSalaryReport();
            String teacherCode = teacher.getTeacherCode();
            TeacherType teacherType = teacher.getTeacherType();
            long fixedSalary = teacher.getFixedSalary();
            long perCreditSalary = teacher.getPerCreditSalary();
            teacher = Teacher.builder().id(user.getId()).firstname(firstname).lastname(lastname).nationalCode(nationalCode)
                    .phoneNumber(phoneNumber).username(username).password(password).email(emailAddress)
                    .teacherCode(teacherCode).teacherType(teacherType)
                    .fixedSalary(fixedSalary).perCreditSalary(perCreditSalary)
                    .presentedCourses(presentedCourses).salaryReport(salaryReport).build();
            teacherService.saveOrUpdate(teacher, teacher.getPresentedCourses().stream().toList(), teacher.getSalaryReport());
            if (teacher != null)
                printer.printResult("ACCOUNT INFORMATION UPDATED", teacher.toString());
        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }

    private void showTeacherCourseMenu() {
        while (true) {
            try{
                printer.printMenu(Constants.TEACHER_COURSE_MENU);
                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1 -> showPresentedCoursesList();
                    case 2 -> addPresentedCourse((Teacher)user);
                    case 3 -> removePresentedCourse((Teacher) user);
                    case 4 -> {
                        return;
                    }
                    default -> printer.printError("Wrong entry!");
                }
            } catch (Exception e){
                printer.printError(e.getMessage());
                input.nextLine();
            }
        }
    }

    private void showPresentedCoursesList(){
        try{
            Teacher teacher = (Teacher) user;
            List<String> presenting = teacher.getPresentedCourses().stream().map(Objects::toString).toList();
            printer.printResult("PRESENTING COURSES LIST", presenting);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void addPresentedCourse(Teacher teacher){
        try {
            printer.getInput("Course id");
            int courseId = input.nextInt();
            Course addedCourse = courseService.findById(courseId);
            teacher.getPresentedCourses().add(addedCourse);
            teacher.calculateTotalSalary();
            teacher.getSalaryReport().setSalaryAmount(teacher.getTotalSalary());
            teacher = teacherService.saveOrUpdate(teacher,teacher.getPresentedCourses().stream().toList(),teacher.getSalaryReport());
            if(teacher!=null)
                printer.printResult("NEW PRESENTING LIST",teacher.getPresentedCourses().stream().map(Objects::toString).toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void removePresentedCourse(Teacher teacher){
        try {
            printer.getInput("Course id");
            int courseId = input.nextInt();
            Course removedCourse = courseService.findById(courseId);
            if(!teacher.getPresentedCourses().contains(removedCourse))
                throw new NotFoundException("This course is not in your presenting list");
            teacher.getPresentedCourses().remove(removedCourse);
            teacher.calculateTotalSalary();
            teacher.getSalaryReport().setSalaryAmount(teacher.getTotalSalary());
            teacher = teacherService.saveOrUpdate(teacher,teacher.getPresentedCourses().stream().toList(),teacher.getSalaryReport());
            if(teacher!=null)
                printer.printResult("NEW PRESENTING LIST",teacher.getPresentedCourses().stream().map(Objects::toString).toList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getTeacherSalaryReport(Teacher teacher){
        printer.printResult("TEACHER SALARY REPORT", teacherService.getSalaryReport(teacher));
    }

    private void setScores(Teacher teacher){
        try {
            printer.getInput("Course id");
            long courseId = input.nextLong();
            Map<String,Float> studentsScores = new HashMap<>();
            Course course = courseService.findById(courseId);
            if(!teacher.getPresentedCourses().contains(course))
                throw new NotFoundException(Constants.DID_NOT_PRESENT_COURSE);
            List<Score> courseScores = scoreService.getCourseScores(course);
            if(courseScores.isEmpty())
                throw new NotFoundException(Constants.COURSE_WITHOUT_STUDENT);
            courseScores.stream()
                    .map(Score::getStudent)
                    .forEach(student -> {
                        printer.getInput(student.getFirstname() + " " + student.getLastname() +
                                "(studentCode: " + student.getStudentCode() + ")" );
                        Float value = input.nextFloat();
                        studentsScores.put(student.getStudentCode(),value);
                    });
            courseScores.forEach(score -> {
                score.setValue(studentsScores.get(score.getStudent().getStudentCode()));
                if(score.getValue()>=10)
                    score.setPassed(true);
                scoreService.saveOrUpdate(score,score.getStudent(),score.getCourse());
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void showStudentMenu() {
        while (true) {
            try{
                printer.printMenu(Constants.STUDENT_MAIN_MENU);
                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1 -> showStudentInfoMenu();
                    case 2 -> getCourse((Student) user);
                    case 3 -> showStudentCurrentSemesterCourses();
                    case 4 -> showStudentAllCourses();
                    case 5 -> {
                        return;
                    }
                    default -> printer.printError("Wrong entry!");
                }
            } catch (Exception e){
                printer.printError(e.getMessage());
                input.nextLine();
            }
        }
    }

    private void showStudentInfoMenu() {
        while (true) {
            try{
                printer.printMenu(Constants.TEACHER_ACCOUNT_MENU);
                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1 -> showStudentAccount();
                    case 2 -> updateStudentAccount();
                    case 3 -> {
                        return;
                    }
                    default -> printer.printError("Wrong entry!");
                }
            } catch (Exception e){
                printer.printError(e.getMessage());
                input.nextLine();
            }
        }
    }

    private void showStudentAccount() {
        try {
            Student student = studentService.findById(user.getId());
            printer.printResult("YOUR ACCOUNT INFO", student.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateStudentAccount() {
        try {
            Student student = studentService.findById(user.getId());
            printer.getInput("new name");
            String firstname = input.nextLine();
            printer.getInput("new lastname");
            String lastname = input.nextLine();
            printer.getInput("new National code");
            String nationalCode = input.next();
            input.nextLine();
            printer.getInput("new phone number");
            String phoneNumber = input.next();
            input.nextLine();
            printer.getInput("new email address");
            String emailAddress = input.next();
            input.nextLine();
            printer.getInput("new username");
            String username = input.next();
            input.nextLine();
            printer.getInput("new password");
            String password = input.next();
            input.nextLine();
            String studentCode = student.getStudentCode();
            int entranceSemesterNumber = student.getEntranceSemesterNumber();
            student = Student.builder().id(user.getId()).firstname(firstname).lastname(lastname).nationalCode(nationalCode)
                    .phoneNumber(phoneNumber).username(username).password(password).email(emailAddress)
                    .studentCode(studentCode).entranceSemesterNumber(entranceSemesterNumber).build();
            student = studentService.saveOrUpdate(student);
            if (student != null)
                printer.printResult("ACCOUNT INFORMATION UPDATED", student.toString());
        } catch (Exception e) {
            printer.printError(e.getMessage());
        }

    }

    private void getCourse(Student student){
        try {
            printer.getInput("Course id");
            long courseId = input.nextLong();
            Course course = courseService.findById(courseId);
            int currentCredits = scoreService.getCurrentSemesterCredits(student);
            if (currentCredits==0) {
                student.setCreditsLimit(studentService.calculatePreviousSemesterAverage(student));
                studentService.saveOrUpdate(student);
            }
            if(student.isAllowed(course.getCredits(),currentCredits) && !scoreService.isTaken(course,student) && !scoreService.isPassed(course,student)){
                Score score = new Score(course,student);
                scoreService.saveOrUpdate(score,score.getStudent(),score.getCourse());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showStudentCurrentSemesterCourses(){
        try{
            List<String> result = courseService.getCurrentSemesterCourses((Student) user).stream().map(Objects::toString).toList();
            printer.printResult("YOUR CURRENT SEMESTER COURSES",result);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void showStudentAllCourses(){
        printer.printResult("YOUR COURSE HISTORY", scoreService.getCourseHistory((Student) user));
    }

}
