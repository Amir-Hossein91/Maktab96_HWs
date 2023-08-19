package entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("employee")
@SequenceGenerator(name = "idGenerator", sequenceName = "employeeSequence")
public class Employee extends UniversityStaff{

    private String employeeCode;
    @OneToOne(cascade = CascadeType.REMOVE)
    private SalaryReport salaryReport;

    public Employee(String firstname, String lastname, String nationalCode, String phoneNumber,
                    String username, String password, String email, String employeeCode, long salary) {
        super(firstname, lastname, nationalCode, phoneNumber, username, password, email);
        this.employeeCode = employeeCode;
        setTotalSalary(salary);
        salaryReport = new SalaryReport(this);
    }
}
