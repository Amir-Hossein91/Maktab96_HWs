package entity;

import entity.baseEntity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@SequenceGenerator(name = "idGenerator", sequenceName = "salaryReportSequence")
public class SalaryReport extends BaseEntity {
    @OneToOne
    private UniversityStaff owner;
    @NotNull(message = "Specify the salary amount")
    private long salaryAmount;

    public SalaryReport(UniversityStaff owner) {
       this.owner = owner;
       salaryAmount = owner.getTotalSalary();
    }

    public String toString() {
        return  this.getSalaryAmount() + "";
    }
}
