import entity.Loan;
import entity.Student;
import entity.enums.LoanType;
import jdk.dynalink.linker.LinkerServices;
import service.LoanService;
import service.StudentService;
import ui.Menu;
import utility.ApplicationContext;
import utility.Printer;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        Printer printer = new Printer();
        Menu menu = new Menu();
        menu.showRegistryMenu();
//        LoanService loanService = ApplicationContext.loanService;
//        StudentService studentService = ApplicationContext.studentService;
//        Student student = studentService.findByNationalCode("3060113947");
////        System.out.println(student.getId());
//        Map<LoanType,Long> loans = new HashMap<>();
//        /*Collection<Loan> loanList = */loanService.getPossibleLoans(student).values().forEach(loan -> {
//            loans.put(loan.getLoanType(), loan.getAmount());
//        });

//        printer.printListWithoutSelect(loans.entrySet().stream().map(Object::toString).toList());

//        System.out.println(ApplicationContext.currentDateCalendar.get(Calendar.MONTH));


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
