package utility;

import java.util.Date;
import java.util.GregorianCalendar;

public class ApplicationContext {
    public static Date currentDate ;

    public static Printer printer;

    static{
        currentDate = new GregorianCalendar(2023,02,19).getTime();
        printer = new Printer();
    }
}
