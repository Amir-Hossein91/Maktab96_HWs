package entity;

import entity.enums.TeacherType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
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