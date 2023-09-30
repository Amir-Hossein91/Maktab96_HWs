package question1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import question1.entity.Person;
import question1.service.impl.PersonServiceImpl;
import question1.utility.ApplicationContext;
import question1.service.impl.StudentServiceImpl;
import question1.service.impl.TeacherServiceImpl;

public class Main {
    public static PersonServiceImpl personService = ApplicationContext.personService;
    public static StudentServiceImpl studentService = ApplicationContext.studentService;
    public static TeacherServiceImpl teacherService = ApplicationContext.teacherService;
    public static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("*********(new run)**********");
        System.out.println(personService.signUp("md",null));

//        System.out.println(personService.signUp("mohammad", "heidari"));

//        System.out.println(teacherService.signUp("amin","kabiri","36261", Degree.PHD, FacultyLevel.MASTER));

//        System.out.println(studentService.signUp("omid","ghanbari","36254", StudyField.IT));

        //////////////////////////////////////////////////////////////////////////////////////////////////////

//        System.out.println(personService.contains(new Person(1,"mohammad","heidari",null)));

//        System.out.println(teacherService.contains(new Teacher(2,"amin","kabiri",null,"36261",Degree.PHD,FacultyLevel.MASTER,0)));

//        System.out.println(studentService.contains(new Student(3,"ali","ghanbari",null,"36254", StudyField.IT,2010)));

        ///////////////////////////////////////////////////////////////////////////////////////////////////////

//        personService.loadAll().forEach(System.out::println);

//        teacherService.loadAll().forEach(System.out::println);

//        studentService.loadAll().forEach(System.out::println);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        personService.delete(new Person(3,"amin","hosseini",null));

//        personService.update(new Person(5,"amin", "hosseini", null));






    }
}
