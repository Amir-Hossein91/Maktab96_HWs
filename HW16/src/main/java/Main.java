import entity.Student;
import entity.enums.Province;
import entity.enums.ResidenceStatus;
import exceptions.InvalidDateException;
import exceptions.NotSavedException;
import repository.StudentRepositoryImpl;
import ui.Menu;
import utility.ApplicationContext;
import utility.Constants;
import utility.Printer;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Menu menu = new Menu();
        menu.showRegistryMenu();
        menu.showLoginMenu();


//        StudentRepositoryImpl studentRepository = new StudentRepositoryImpl(Student.class);
//        System.out.println(studentRepository.getClass().getSimpleName());

//        Scanner scanner = new Scanner(System.in);
//        try{
//            int number = scanner.nextInt();
//        }catch (InputMismatchException e){
//            System.out.println(e.getMessage());
//        }

//        System.out.println("استان خود را از لیست زیر انتخاب کنید:");


//        GregorianCalendar gregorianCalendar = new GregorianCalendar(2023, 0, 15);
//        Date date = gregorianCalendar.getTime();
//        String formattedDate = new SimpleDateFormat("yyyy/MM/dd").format(date);
//
//        System.out.println(formattedDate);
//        System.out.println(gregorianCalendar.get(Calendar.YEAR));
//        System.out.println(ApplicationContext.gregorianCalendar.get(1));
//        System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(ApplicationContext.currentDate));

//        System.out.println(Province.values()[4]);

//        printer.printListWithoutSelect(Arrays.stream(Province.values()).map(Objects::toString).toList());
//        System.out.println(Province.values()[33]);

//        boolean metropolis = Province.values()[3].isMetropolis();

//        System.out.println(Integer.parseInt(new SimpleDateFormat("yyyy").format(date)) + 5);

//        printer.printListWithSelect(Arrays.asList("Dorm","Own house"));
//
//        System.out.println(ResidenceStatus.DORM.ordinal());

    }
}
