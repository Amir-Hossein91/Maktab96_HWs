package utility;

import entity.Employee;
import entity.Teacher;
import repository.*;
import service.impl.*;


public class ApplicationContext {
    private static final CourseRepositroyImpl courseRepositroy;
    private static final EmployeeRepositoryImpl employeeRepository;
    private static final PersonRepositoryImpl personRepository;
    private static final SalaryReportRepositoryImpl<Employee> employeeSalaryReportRepository;
    private static final SalaryReportRepositoryImpl<Teacher> teacherSalaryReportRepository;
    private static final ScoreRepositoryImpl scoreRepository;
    private static final StudentRepositoryImpl studentRepository;
    private static final TeacherRepositoryImpl teacherRepository;
    public static final CourseServiceImpl courseService;
    public static final EmployeeServiceImpl employeeService;
    public static final PersonServiceImpl personService;
    public static final SalaryReportServiceImpl<Employee> employeeSalaryReportService;
    public static final SalaryReportServiceImpl<Teacher> teacherSalaryReportService;
    public static final ScoreServiceImpl scoreService;
    public static final StudentServiceImpl studentService;
    public static final TeacherServiceImpl teacherService;

    static {
        courseRepositroy = new CourseRepositroyImpl();
        employeeRepository = new EmployeeRepositoryImpl();
        personRepository = new PersonRepositoryImpl();
        employeeSalaryReportRepository = new SalaryReportRepositoryImpl<>();
        teacherSalaryReportRepository = new SalaryReportRepositoryImpl<>();
        scoreRepository = new ScoreRepositoryImpl();
        studentRepository = new StudentRepositoryImpl();
        teacherRepository = new TeacherRepositoryImpl();
        courseService = new CourseServiceImpl(courseRepositroy);
        employeeService = new EmployeeServiceImpl(employeeRepository);
        personService = new PersonServiceImpl(personRepository);
        employeeSalaryReportService = new SalaryReportServiceImpl<>(employeeSalaryReportRepository);
        teacherSalaryReportService = new SalaryReportServiceImpl<>(teacherSalaryReportRepository);
        scoreService = new ScoreServiceImpl(scoreRepository);
        studentService = new StudentServiceImpl(studentRepository);
        teacherService = new TeacherServiceImpl(teacherRepository);
    }
}
