package entity;


import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("student")
public class Student extends Person{
    private long studentCode;
    private int entranceSemesterNumber;
    private int unitsLimit;

    public Student(String firstname, String lastname, String nationalCode,
                   String phoneNumber, String username, String password, String email,
                   long studentCode, int entranceSemesterNumber) {
        super(firstname, lastname, nationalCode, phoneNumber, username, password, email);
        this.studentCode = studentCode;
        this.entranceSemesterNumber = entranceSemesterNumber;
        unitsLimit = 20;
    }

}
