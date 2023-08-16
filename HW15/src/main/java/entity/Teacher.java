package entity;

import entity.enums.TeacherType;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("teacher")
public class Teacher extends Person{
    private String username;
    private String password;
    private long teacherCode;
    private TeacherType teacherType;
    private long fixedSalary;
    private long perHourSalary;
    @ManyToMany
    private Set<Course> presentedCourses;
    @ManyToMany
    private Set<Student> courseStudents;
    @OneToMany
    private Set<SalaryReport> salaryReport;
}
