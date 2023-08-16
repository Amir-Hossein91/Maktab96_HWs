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
}
