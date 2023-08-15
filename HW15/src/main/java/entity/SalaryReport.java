package entity;

import entity.enums.StaffType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class SalaryReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private StaffType owner;
    private Date reportDate;
    private long salaryAmount;
}
