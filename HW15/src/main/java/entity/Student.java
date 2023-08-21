package entity;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("student")
public class Student extends Person{
    private String studentCode;
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

}
