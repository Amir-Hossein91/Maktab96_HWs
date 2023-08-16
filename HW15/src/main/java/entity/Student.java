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

}
