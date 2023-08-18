package entity;

import entity.baseEntity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class SalaryReport<T extends UniversityStaff> extends BaseEntity {
    @OneToOne
    private T owner;
    private Date reportDate;
    private long salaryAmount;

    public SalaryReport(T owner) {
       this.owner = owner;
        reportDate = new Date();
        salaryAmount = owner.getTotalSalary();
    }
}
