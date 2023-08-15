package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Employee extends Person{
    private String username;
    private String password;
    private String employeeCode;
    private long salary;
    @OneToMany
    private Set<SalaryReport> salaryReport;
}
