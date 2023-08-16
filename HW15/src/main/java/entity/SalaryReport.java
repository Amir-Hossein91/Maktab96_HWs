package entity;

import entity.enums.StaffType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Entity
public class SalaryReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private StaffType owner;
    private Date reportDate;
    private long salaryAmount;
}
