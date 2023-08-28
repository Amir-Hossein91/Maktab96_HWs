package entity;

import java.util.Date;
import java.util.List;

public class Student {
    private long id;
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
    private BankAccount bankAccount;
    private Province province;
    private boolean isMarried;
    private String spouseName;
    private String spouseNationalCode;
    private boolean inDorm;
    private String houseContractNumber;
    private String houseAddress;
    private Date graduateYear;
    private List<Loan> loans;
}
