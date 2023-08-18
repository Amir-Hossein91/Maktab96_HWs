package entity;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("employee")
public class Employee extends UniversityStaff{

    private String employeeCode;
    @OneToOne
    private SalaryReport<Employee> salaryReport;

    public Employee(String firstname, String lastname, String nationalCode, String phoneNumber,
                    String username, String password, String email, String employeeCode, long salary) {
        super(firstname, lastname, nationalCode, phoneNumber, username, password, email);
        this.employeeCode = employeeCode;
        setTotalSalary(salary);
        salaryReport = new SalaryReport<>(this);
    }
}
