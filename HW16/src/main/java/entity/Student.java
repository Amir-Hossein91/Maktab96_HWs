package entity;

import entity.base.BaseEntity;
import entity.enums.AcademicGrade;
import entity.enums.AcceptanceType;
import entity.enums.Province;
import entity.enums.UniversityType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.util.Date;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@SequenceGenerator(name = "idGenerator" , sequenceName = "student_Sequence")
public class Student extends BaseEntity {
    private String firstname;
    private String lastname;
    private String fathername;
    private String mothername;
    private String identityCode;
    private String nationalCode;
    private Date birthDate;
    private String studentNumbdr;
    private UniversityType universityType;
    private Date entranceYear;
    private AcademicGrade academicGrade;
    private AcceptanceType acceptanceType;
    @OneToOne
    private BankAccount bankAccount;
    private Province province;
    private boolean isMarried;
    private String spouseName;
    private String spouseNationalCode;
    private boolean inDorm;
    private String houseContractNumber;
    private String houseAddress;
    private Date graduateYear;
    @OneToMany(mappedBy = "loaner")
    private List<Loan> loans;
    private Date loanRegesterDate;
}
