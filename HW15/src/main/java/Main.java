import entity.Course;
import entity.SalaryReport;
import entity.Student;
import entity.Teacher;
import entity.enums.StaffType;
import entity.enums.TeacherType;
import service.impl.*;
import utility.ApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final CourseServiceImpl courseService = ApplicationContext.courseService;
    private static final EmployeeServiceImpl employeeService = ApplicationContext.employeeService;
    private static final PersonServiceImpl personService = ApplicationContext.personService;
    private static final SalaryReportServiceImpl salaryReportService = ApplicationContext.salaryReportService;
    private static final ScoreServiceImpl scoreService = ApplicationContext.scoreService;
    private static final StudentServiceImpl studentService = ApplicationContext.studentService;
    private static final TeacherServiceImpl teacherService = ApplicationContext.teacherService;

    public static void main(String[] args) {
        SalaryReport salaryReport = new SalaryReport(StaffType.EMPLOYEE,new Date(),10_000_000);
        SalaryReport salaryReport1 = new SalaryReport(StaffType.TEACHER,new Date(), 15000000);

        Teacher teacher1 = new Teacher(5,"amir","ahmadi","3060113947","09359087710","amir@yahoo.com","amirahmadi",
                "123456",14021, TeacherType.FACULTY_MEMBER,10000000,1000000,
                new HashSet<Course>(),new HashSet<Student>(),new HashSet<SalaryReport>());
        List<Course> presentedCourses = new ArrayList<>();
        Course course1 = new Course("physics",2);
        Course course2 = new Course("Programming",3);
        presentedCourses.add(course1);
        presentedCourses.add(course2);

        Student student1 = new Student(2,"ali","mozaffari","5654165","65654654","lkhsdflk",
                "aliMozaffari","123456",140215,4021,50,14,20, new HashSet<>(presentedCourses),
                new HashSet<>(presentedCourses), 15,16);

        try{
            studentService.saveOrUpdate(student1,presentedCourses);
            teacherService.saveOrUpdate(teacher1,presentedCourses,salaryReport);
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }

    }
}
