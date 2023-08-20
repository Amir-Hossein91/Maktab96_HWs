package entity;

import entity.baseEntity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/*(callSuper = true)*/
@EqualsAndHashCode(callSuper = true)
@Entity
@SequenceGenerator(name = "idGenerator", sequenceName = "salaryReportSequence")
public class SalaryReport extends BaseEntity {
    @OneToOne
    private UniversityStaff owner;
    @Temporal(value = TemporalType.DATE)
    @Column(name = "report_date")
    private Date reportDate;
    @NotNull(message = "Specify the salary amount")
    private long salaryAmount;

    public SalaryReport(UniversityStaff owner) {
       this.owner = owner;
       Calendar calendar = new GregorianCalendar();
       reportDate =calendar.getTime();
       salaryAmount = owner.getTotalSalary();
    }

    public String toString() {
        return "SalaryReport(reportDate=" + this.getReportDate() +
                ", salaryAmount=" + this.getSalaryAmount() +
                ")";
    }
}
