package entity;

import entity.base.BaseEntity;
import entity.enums.AcademicGrade;
import entity.enums.AcceptanceType;
import entity.enums.Province;
import entity.enums.UniversityType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import utility.Constants;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @Pattern(regexp = "^[a-zA-Z\s]{3,}$",message = Constants.INVALID_NAME_FORMAT)
    private String firstname;
    @Pattern(regexp = "^[a-zA-Z\s]{3,}$",message = Constants.INVALID_LASTNAME_FORMAT)
    private String lastname;
    @Pattern(regexp = "^[a-zA-Z\s]{3,}$",message = Constants.INVALID_NAME_FORMAT)
    private String fathername;
    @Pattern(regexp = "^[a-zA-Z\s]{3,}$",message = Constants.INVALID_NAME_FORMAT)
    private String mothername;
    @Pattern(regexp = "\\d{1,10}" , message = Constants.INVALID_IDENTITY_CODE_FORMAT)
    @Column(unique = true)
    private String identityCode;
    @Column(unique = true)
    @Pattern(regexp = "\\d{10}", message = Constants.INVALID_NATIONAL_CODE_FORMAT)
    private String nationalCode;
    private Date birthDate;
    @Column(unique = true)
    @Pattern(regexp = "^[0-9]{8}$",message = Constants.INVALID_STUDENT_CODE_FORMAT)
    private String studentNumber;
    @Enumerated(value = EnumType.STRING)
    private UniversityType universityType;
    @Pattern(regexp = "[12][\\d]{3}", message = Constants.INVALID_ENTRANCE_YEAR_FORMAT)
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
    @Pattern(regexp = "^[a-zA-Z\s]{3,}$",message = Constants.INVALID_NAME_FORMAT)
    private String spouseFirstName;
    @Pattern(regexp = "^[a-zA-Z\s]{3,}$",message = Constants.INVALID_LASTNAME_FORMAT)
    private String spouseLastName;
    @Pattern(regexp = "\\d{10}", message = Constants.INVALID_NATIONAL_CODE_FORMAT)
    private String spouseNationalCode;
    private boolean inDorm;
    @Pattern(regexp = "^[0-9]{6,9}", message = Constants.INVALID_HOUSE_RENT_CONTRACT_NUMBER_FORMAT)
    private String houseContractNumber;
    @Size(min = 3, max = 100, message = Constants.INVALID_ADDRESS_FORMAT)
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
