package entity;

import entity.base.BaseEntity;
import entity.enums.AcademicGrade;
import entity.enums.LoanType;
import entity.enums.Province;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@SequenceGenerator(name = "idGenerator" , sequenceName = "loan_Sequence")
public class Loan extends BaseEntity {
    @Enumerated(value = EnumType.STRING)
    private LoanType loanType;
    private Long amount;
    @ManyToOne
    private Student borrower;
    private LocalDate registrationDate;

    public Loan (LoanType loanType, Student borrower){
        this.loanType = loanType;
        this.borrower = borrower;
        setLoanAmount(loanType,borrower);
    }

    private void setLoanAmount(LoanType loanType, Student borrower){
        switch (loanType){
            case EDUCATIONAL -> {
                AcademicGrade academicGrade = borrower.getAcademicGrade();
                if(academicGrade == AcademicGrade.ASSOCIATE || academicGrade == AcademicGrade.INTERRUPTED_BACHELOR
                        || academicGrade == AcademicGrade.UNINTERRUPTED_BACHELOR)
                    amount = 1_900_000L;
                else if(academicGrade == AcademicGrade.INTERRUPTED_DOCTORATE)
                    amount = 2_600_000L;
                else
                    amount = 2_250_000L;
            }
            case TUITION -> {
                AcademicGrade academicGrade = borrower.getAcademicGrade();
                if(academicGrade == AcademicGrade.ASSOCIATE || academicGrade == AcademicGrade.INTERRUPTED_BACHELOR
                        || academicGrade == AcademicGrade.UNINTERRUPTED_BACHELOR)
                    amount = 1_300_000L;
                else if(academicGrade == AcademicGrade.INTERRUPTED_DOCTORATE)
                    amount = 6_500_000L;
                else
                    amount = 2_600_000L;
            }
            case HOUSE_RENT -> {
                Province province = borrower.getProvince();
                if (province == Province.TEHRAN)
                    amount = 32_000_000L;
                else if(province.isMetropolis())
                    amount = 26_000_000L;
                else
                    amount = 19_500_000L;
            }
            default -> {}
        }
    }

    public String toString() {
        return "Loan(loanType=" + this.getLoanType() +
                ", amount=" + this.getAmount() +
                ", borrowerId=" + this.getBorrower().getId() +
                ", registrationDate=" + this.getRegistrationDate() + ")";
    }
}
