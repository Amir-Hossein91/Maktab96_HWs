package entity;

import entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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
    private Date dueDate;
    private Double amount;
    @ManyToOne
    private Loan loan;
    private boolean isPaid;
    private Date paidDate;

    public Debt (Loan loan){
        this.loan = loan;
        this.isPaid = false;
    }
}
