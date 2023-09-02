package entity;

import entity.base.BaseEntity;
import entity.enums.Availability;
import entity.enums.LoanType;
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
@SequenceGenerator(name = "idGenerator" , sequenceName = "loan_Sequence")
public class Loan extends BaseEntity {
    private LoanType loanType;
    private Long amount;
    private Availability availability;
    @ManyToOne
    private Student loaner;
}