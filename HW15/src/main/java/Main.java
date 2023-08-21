
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import entity.*;
import exceptions.NotFoundException;
import service.impl.*;
import ui.Menu;
import utility.ApplicationContext;
import utility.Constants;
import utility.Printer;

import javax.swing.plaf.TableHeaderUI;
import java.util.List;


public class Main {
    private static final CourseServiceImpl courseService = ApplicationContext.courseService;
    private static final EmployeeServiceImpl employeeService = ApplicationContext.employeeService;
    private static final PersonServiceImpl personService = ApplicationContext.personService;
    private static final SalaryReportServiceImpl salaryReportService = ApplicationContext.salaryReportService;
    private static final ScoreServiceImpl scoreService = ApplicationContext.scoreService;
    private static final StudentServiceImpl studentService = ApplicationContext.studentService;
    private static final TeacherServiceImpl teacherService = ApplicationContext.teacherService;

    public static void main(String[] args) {

//        Course course1 = new Course("java programming",3,4);
//        Course course2 = new Course("physics",2,4);
//        Teacher teacher1 = new Teacher("amirhossein","ahmadinezhad","3060113947","09359087710","amir_ahmadi9034@yahoo.com"
//        ,"AmirHossein","123456","14020528", TeacherType.FACULTY_MEMBER,10_000_000,1_000_000);
//        teacher1.getPresentedCourses().add(course1);
//        teacher1.getPresentedCourses().add(course2);
//
//        Person person1 = new Person("ali","heydari","646546","0654654","Aliiii","65464","ali@gmail.com");
//        Person person2 = new Person("amir","lskjdf","646546","0654654","Alilskdfjiii","65464","alsdfsi@gmail.com");
//
//        Student student1 = new Student("masood","Jafari","6546565","6465465","Massssood","6354165","lksdf",
//                "6354354",1);
//
//        SalaryReport salaryReport1 = new SalaryReport(teacher1);
//
//        Employee employee1 = new Employee("admin","admin","0000000000","09000000000","admin",
//                "Admin00","admin@admin.com","14020501",0);
//        Teacher teacher3 = new Teacher("admin","admin","0000000001","09000000001","adminn@admin.com","adminn",
//                "Admin01","14020502",TeacherType.FACULTY_MEMBER,0,0);
////
//        SalaryReport salaryReport2 = new SalaryReport(employee1);
//
//        Score score1 = new Score(course1,student1,17.06F);
//
//        Printer printer = new Printer();
//        printer.printMenu(Constants.TEACHER_MAIN_MENU);
//        printer.printMenu(Constants.EMPLOYEE_TEACHER_MENU);
//
//
//        try{
//            personService.saveOrUpdate(person1);
//            personService.saveOrUpdate(person2);
//            courseService.saveOrUpdate(course1);
//            teacherService.saveOrUpdate(teacher3,teacher3.getPresentedCourses().stream().toList(),teacher3.getSalaryReport());
//            studentService.saveOrUpdate(student1,teacher1.getPresentedCourses().stream().toList());
////            salaryReportService.saveOrUpdate(salaryReport1);
////            salaryReportService.saveOrUpdate(salaryReport2);
//            employeeService.saveOrUpdate(employee1,employee1.getSalaryReport());
//            scoreService.saveOrUpdate(score1,student1,course1);
//            List<Person> all = personService.findAll();
//            for(Person p: all){
//                p.setPassword("147258369");
//                personService.saveOrUpdate(p);
//            }
//
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }

        Menu menu = new Menu();
        menu.begin();

//        try {
//            System.out.println(personService.findById(1));
//        } catch (NotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }
}
