package entity;

import entity.baseEntity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@SequenceGenerator(name = "idGenerator", sequenceName = "salaryReportSequence")
public class SalaryReport extends BaseEntity {
    @OneToOne
    private UniversityStaff owner;
    private Date reportDate;
    private long salaryAmount;

    public SalaryReport(UniversityStaff owner) {
       this.owner = owner;
        reportDate = new Date();
        salaryAmount = owner.getTotalSalary();
    }
}
