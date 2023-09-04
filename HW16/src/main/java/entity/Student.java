package entity;

import entity.base.BaseEntity;
import entity.enums.AcademicGrade;
import entity.enums.AcceptanceType;
import entity.enums.Province;
import entity.enums.UniversityType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
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
    @Column(unique = true)
    private String identityCode;
    @Column(unique = true)
    private String nationalCode;
    private Date birthDate;
    @Column(unique = true)
    @Pattern(regexp = "^[0-9]{8}$",message = "Invalid student code format!")
    private String studentNumber;
    @Enumerated(value = EnumType.STRING)
    private UniversityType universityType;
    private int entranceYear;
    @Enumerated(value = EnumType.STRING)
    private AcademicGrade academicGrade;
    @Enumerated(value = EnumType.STRING)
    private AcceptanceType acceptanceType;
    @OneToOne(cascade = CascadeType.PERSIST)
    private BankAccount bankAccount;
    @Enumerated(value = EnumType.STRING)
    private Province province;
    private boolean isMarried;
    private String spouseFirstName;
    private String spouseLastName;
    private String spouseNationalCode;
    private boolean inDorm;
    @Pattern(regexp = "^[0-9]{6,9}")
    private String houseContractNumber;
    private String houseAddress;
    private int graduateYear;
    @OneToMany(mappedBy = "loanee")
    private List<Loan> loans;
    private String username;
    private String password;

    public void setBirthDate(int year,int month, int day){
        birthDate = new GregorianCalendar(year,month-1,day).getTime();
    }

    public int getBirthYear(){
        return Integer.parseInt(new SimpleDateFormat("yyyy").format(birthDate));
    }

}
