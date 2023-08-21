package entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@SuperBuilder
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
    @Column(unique = true)
    @Pattern(regexp = "^[0-9]{6,8}$",message = "Invalid employeeCode format!")
    private String employeeCode;
    @OneToOne(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private SalaryReport salaryReport;

    public Employee(String firstname, String lastname, String nationalCode, String phoneNumber,
                    String username, String password, String email, String employeeCode, long salary) {
        super(firstname, lastname, nationalCode, phoneNumber, username, password, email);
        this.employeeCode = employeeCode;
        setTotalSalary(salary);
        salaryReport = new SalaryReport(this);
    }
}
