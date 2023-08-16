package utility;

import repository.*;
import service.impl.*;


public class ApplicationContext {
    private static final CourseRepositroyImpl courseRepositroy;
    private static final EmployeeRepositoryImpl employeeRepository;
    private static final PersonRepositoryImpl personRepository;
    private static final SalaryReportRepositoryImpl salaryReportRepository;
    private static final ScoreRepositoryImpl scoreRepository;
    private static final StudentRepositoryImpl studentRepository;
    private static final TeacherRepositoryImpl teacherRepository;
    public static final CourseServiceImpl courseService;
    public static final EmployeeServiceImpl employeeService;
    public static final PersonServiceImpl personService;
    public static final SalaryReportServiceImpl salaryReportService;
    public static final ScoreServiceImpl scoreService;
    public static final StudentServiceImpl studentService;
    public static final TeacherServiceImpl teacherService;

    static {
        courseRepositroy = new CourseRepositroyImpl();
        employeeRepository = new EmployeeRepositoryImpl();
        personRepository = new PersonRepositoryImpl();
        salaryReportRepository = new SalaryReportRepositoryImpl();
        scoreRepository = new ScoreRepositoryImpl();
        studentRepository = new StudentRepositoryImpl();
        teacherRepository = new TeacherRepositoryImpl();
        courseService = new CourseServiceImpl(courseRepositroy);
        employeeService = new EmployeeServiceImpl(employeeRepository);
        personService = new PersonServiceImpl(personRepository);
        salaryReportService = new SalaryReportServiceImpl(salaryReportRepository);
        scoreService = new ScoreServiceImpl(scoreRepository);
        studentService = new StudentServiceImpl(studentRepository);
        teacherService = new TeacherServiceImpl(teacherRepository);
    }
}
