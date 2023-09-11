package entity;

import entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.util.Date;
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@SequenceGenerator(name = "idGenerator" , sequenceName = "dept_Sequence")

public class Debt extends BaseEntity {
    private LocalDate dueDate;
    private Double amount;
    @ManyToOne
    private Loan loan;
    private boolean isPaid;
    private LocalDate paidDate;

    public Debt (Loan loan){
        this.loan = loan;
        this.isPaid = false;
    }
}
