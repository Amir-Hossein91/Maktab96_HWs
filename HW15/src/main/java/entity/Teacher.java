package entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("teacher")
@SequenceGenerator(name = "idGenerator", sequenceName = "teacherSequence")
public class Teacher extends UniversityStaff{
    private String teacherCode;
    private TeacherType teacherType;
    private long fixedSalary;
    private long perCreditSalary;
    @OneToMany/*(mappedBy = "teacher" , cascade = CascadeType.PERSIST)*/
    private Set<Course> presentedCourses;
    @OneToOne(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private SalaryReport salaryReport;


    public Teacher(String firstname, String lastname, String nationalCode, String phoneNumber,
                   String email, String username, String password, String teacherCode, TeacherType teacherType,
                   long fixedSalary, long perCreditSalary) {
        super(firstname, lastname, nationalCode, phoneNumber, username, password, email);
        this.teacherCode = teacherCode;
        this.teacherType = teacherType;
        if(teacherType == TeacherType.FACULTY_MEMBER)
            this.fixedSalary = fixedSalary;
        this.perCreditSalary = perCreditSalary;
        presentedCourses = new HashSet<>();
        calculateTotalSalary();
        salaryReport = new SalaryReport(this);
    }

    public void calculateTotalSalary(){
       setTotalSalary(presentedCourses
               .stream()
               .map(Course::getCredits)
               .reduce(0, Integer::sum)
               * perCreditSalary + fixedSalary);
    }


}
