package entity;

import entity.base.BaseEntity;
import entity.enums.Availability;
import entity.enums.LoanType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
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
    @Enumerated(value = EnumType.STRING)
    private LoanType loanType;
    private Long amount;
    @Enumerated(value = EnumType.STRING)
    private Availability availability;
    @ManyToOne
    private Student loanee;
    private Date registrationDate;
}
