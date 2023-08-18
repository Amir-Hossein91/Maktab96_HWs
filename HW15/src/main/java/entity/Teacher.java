package entity;

import entity.enums.TeacherType;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
    @Cascade(value = CascadeType.DELETE)
    private Set<Course> presentedCourses;
    @ManyToMany
    private Set<Student> courseStudents;
    @OneToMany
    private Set<SalaryReport> salaryReport;

    public Teacher(String firstname, String lastname, String nationalCode, String phoneNumber,
                   String email, String username, String password, long teacherCode, TeacherType teacherType,
                   long fixedSalary, long perHourSalary, Set<Course> presentedCourses,
                   Set<Student> courseStudents, Set<SalaryReport> salaryReport) {
        super(firstname, lastname, nationalCode, phoneNumber, email);
        this.username = username;
        this.password = password;
        this.teacherCode = teacherCode;
        this.teacherType = teacherType;
        this.fixedSalary = fixedSalary;
        this.perHourSalary = perHourSalary;
        this.presentedCourses = presentedCourses;
        this.courseStudents = courseStudents;
        this.salaryReport = salaryReport;
    }

    public Teacher(long id,String firstname, String lastname, String nationalCode, String phoneNumber,
                   String email, String username, String password, long teacherCode, TeacherType teacherType,
                   long fixedSalary, long perHourSalary, Set<Course> presentedCourses,
                   Set<Student> courseStudents, Set<SalaryReport> salaryReport) {
        super(id,firstname, lastname, nationalCode, phoneNumber, email);
        this.username = username;
        this.password = password;
        this.teacherCode = teacherCode;
        this.teacherType = teacherType;
        this.fixedSalary = fixedSalary;
        this.perHourSalary = perHourSalary;
        this.presentedCourses = presentedCourses;
        this.courseStudents = courseStudents;
        this.salaryReport = salaryReport;
    }
}
