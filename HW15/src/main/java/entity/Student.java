package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
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
