package question2;


import question2.service.impl.PersonServiceImpl;
import question2.service.impl.StudentServiceImpl;
import question2.service.impl.TeacherServiceImpl;
import question2.utility.ApplicationContext;

public class Main {
    public static PersonServiceImpl personService = ApplicationContext.personService;
    public static StudentServiceImpl studentService = ApplicationContext.studentService;
    public static TeacherServiceImpl teacherService = ApplicationContext.teacherService;

    public static void main(String[] args) {

        System.out.println(personService.signUp("alireza", "gharibi"));
//
//        System.out.println(personService.contains(new Person(6,"alireza","gharibi",null)));

//        personService.delete(new Person(3,"amin","dehbandi",null));

//        personService.loadAll().forEach(System.out::println);

//        personService.update(new Person(5,"amin", "dehbandi", null));

//        System.out.println(teacherService.signUp("ali","kabiri","362547"));

//        teacherService.loadAll().forEach(System.out::println);

//        studentService.loadAll().forEach(System.out::println);

//        System.out.println(teacherService.contains(new Teacher(7,"ali","kabiri",null,"362547",null,null,0)));


//        System.out.println(studentService.signUp("ali","ghanbari","362547"));


    }
}
