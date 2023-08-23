
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
    private static final EmployeeServiceImpl employeeService = ApplicationContext.employeeService;

    public static void main(String[] args) {
//
        Employee employee1 = new Employee("admin","admin","0000000000","09000000000","admin",
                "Admin00","admin@admin.com","14020501",1000);
            employeeService.saveOrUpdate(employee1,employee1.getSalaryReport());

        Menu menu = new Menu();
        menu.begin();
    }
}
