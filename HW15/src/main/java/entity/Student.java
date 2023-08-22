package entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;
import utility.Constants;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("student")
public class Student extends Person{
    @Column(unique = true)
    @Pattern(regexp = "^[0-9]{6,9}$",message = "Invalid student code format!")
    private String studentCode;
    @Range(max = Constants.CURRENT_SEMESTER_NUMBER, message = "Invalid entrance semester number!")
    private int entranceSemesterNumber;
    private int creditsLimit;

    public Student(String firstname, String lastname, String nationalCode,
                   String phoneNumber, String username, String password, String email,
                   String studentCode, int entranceSemesterNumber) {
        super(firstname, lastname, nationalCode, phoneNumber, username, password, email);
        this.studentCode = studentCode;
        this.entranceSemesterNumber = entranceSemesterNumber;
        creditsLimit = 10;
    }

    public boolean isAllowed(int courseCredit, int currentCredit){
        return (courseCredit + currentCredit) <= creditsLimit;
    }

    public void setCreditsLimit(Float previousAvg){
        if (previousAvg >= 18.00)
            creditsLimit = 24;
        else
            creditsLimit = 20;
    }

    public String toString() {
        return "Student(" +
                super.toString() +
                ", studentCode = " +
                this.getStudentCode() +
                ", entranceSemesterNumber = " +
                this.getEntranceSemesterNumber() +
                ", creditsLimit = " +
                this.getCreditsLimit() +
                ")";
    }
}
