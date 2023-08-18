package entity;


import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
    private String username;
    private String password;
    private long studentCode;
    private int currentSemesterNumber;
    private int totalUnits;
    private int currentSemesterUnits;
    private int unitsLimit;
    @OneToMany
    private Set<Course> passedUnits;
    @OneToMany
    private Set<Course> takenUnits;
    private float currentTermAverage;
    private float totalAverage;

    public Student(String firstname, String lastname, String nationalCode,
                   String phoneNumber, String email, String username, String password,
                   long studentCode, int currentSemesterNumber, int totalUnits, int currentSemesterUnits,
                   int unitsLimit, Set<Course> passedUnits,
                   Set<Course> takenUnits, float currentTermAverage, float totalAverage) {
        super(firstname, lastname, nationalCode, phoneNumber, email);
        this.username = username;
        this.password = password;
        this.studentCode = studentCode;
        this.currentSemesterNumber = currentSemesterNumber;
        this.totalUnits = totalUnits;
        this.currentSemesterUnits = currentSemesterUnits;
        this.unitsLimit = unitsLimit;
        this.passedUnits = passedUnits;
        this.takenUnits = takenUnits;
        this.currentTermAverage = currentTermAverage;
        this.totalAverage = totalAverage;

    }
    public Student(long id,String firstname, String lastname, String nationalCode,
                   String phoneNumber, String email, String username, String password,
                   long studentCode, int currentSemesterNumber, int totalUnits, int currentSemesterUnits,
                   int unitsLimit, Set<Course> passedUnits,
                   Set<Course> takenUnits, float currentTermAverage, float totalAverage) {
        super(id,firstname, lastname, nationalCode, phoneNumber, email);
        this.username = username;
        this.password = password;
        this.studentCode = studentCode;
        this.currentSemesterNumber = currentSemesterNumber;
        this.totalUnits = totalUnits;
        this.currentSemesterUnits = currentSemesterUnits;
        this.unitsLimit = unitsLimit;
        this.passedUnits = passedUnits;
        this.takenUnits = takenUnits;
        this.currentTermAverage = currentTermAverage;
        this.totalAverage = totalAverage;
    }
}
