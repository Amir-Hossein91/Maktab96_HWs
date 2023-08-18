package entity;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("employee")
public class Employee extends Person{
    private String username;
    private String password;
    private String employeeCode;
    private long salary;
    @OneToMany
    private Set<SalaryReport> salaryReport;

    public Employee(String firstname, String lastname, String nationalCode, String phoneNumber,
                    String email, String username, String password, String employeeCode,
                    long salary, Set<SalaryReport> salaryReport) {
        super(firstname, lastname, nationalCode, phoneNumber, email);
        this.username = username;
        this.password = password;
        this.employeeCode = employeeCode;
        this.salary = salary;
        this.salaryReport = salaryReport;
    }
}
