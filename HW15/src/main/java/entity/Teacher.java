package entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("teacher")
public class Teacher extends UniversityStaff{
    private long teacherCode;
    private TeacherType teacherType;
    private long fixedSalary;
    private long perHourSalary;
    @OneToMany(mappedBy = "teacher")
    private Set<Course> presentedCourses;
    @OneToOne
    private SalaryReport<Teacher> salaryReport;


    public Teacher(String firstname, String lastname, String nationalCode, String phoneNumber,
                   String email, String username, String password, long teacherCode, TeacherType teacherType,
                   long fixedSalary, long perHourSalary) {
        super(firstname, lastname, nationalCode, phoneNumber, username, password, email);
        this.teacherCode = teacherCode;
        this.teacherType = teacherType;
        if(teacherType == TeacherType.FACULTY_MEMBER)
            this.fixedSalary = fixedSalary;
        this.perHourSalary = perHourSalary;
        presentedCourses = new HashSet<>();
        calculateTotalSalary();
        salaryReport = new SalaryReport<>(this);
    }

    private void calculateTotalSalary(){
       setTotalSalary(presentedCourses
               .stream()
               .map(Course::getUnits)
               .reduce(0, Integer::sum)
               * perHourSalary + fixedSalary);
    }


}
