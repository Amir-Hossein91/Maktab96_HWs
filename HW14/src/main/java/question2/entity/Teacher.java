package question2.entity;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.UniqueConstraint;
import java.util.Date;
import java.util.Objects;

@Entity
public class Teacher extends Person{
    @NotNull(message = "Teacher code can't be null")
    @Column(unique = true)
    @Digits(integer = 5, fraction = 0,message = "Teacher code must contain only digits")
    @Length(min = 5,max = 5,message = "Teacher code must have 5 digits")
    private String teacherCode;
    @NotNull(message = "Teacher's degree can't be null")
    @Enumerated
    private Degree degree;
    @NotNull(message = "Faculty level can't be null")
    @Enumerated
    private FacultyLevel facultyLevel;
    @Digits(integer = 5,fraction = 2,message = "Invalid salary format")
    private double salary;

    public Teacher() {
    }

    public Teacher(String firstname, String lastname, String teacherCode) {
        super(firstname, lastname);
        this.teacherCode = teacherCode;
    }

    public Teacher(String firstname,
                   String lastname,
                   Date birthDate,
                   String teacherCode,
                   Degree degree,
                   FacultyLevel facultyLevel,
                   double salary) {
        super(firstname, lastname, birthDate);
        this.teacherCode = teacherCode;
        this.degree = degree;
        this.facultyLevel = facultyLevel;
        this.salary = salary;
    }

    public Teacher(long id,
                   String firstname,
                   String lastname,
                   Date birthDate,
                   String teacherCode,
                   Degree degree,
                   FacultyLevel facultyLevel,
                   double salary) {
        super(id, firstname, lastname, birthDate);
        this.teacherCode = teacherCode;
        this.degree = degree;
        this.facultyLevel = facultyLevel;
        this.salary = salary;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public FacultyLevel getFacultyLevel() {
        return facultyLevel;
    }

    public void setFacultyLevel(FacultyLevel facultyLevel) {
        this.facultyLevel = facultyLevel;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Double.compare(teacher.salary, salary) == 0 && teacherCode.equals(teacher.teacherCode) && Objects.equals(degree, teacher.degree) && facultyLevel == teacher.facultyLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), teacherCode, degree, facultyLevel, salary);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id =" + getId() +
                ", firstname ='" + getFirstname() + '\'' +
                ", lastname ='" + getLastname() + '\'' +
                ", birthDate ='" + getBirthDate() + '\'' +
                ", teacherCode ='" + teacherCode + '\'' +
                ", degree ='" + degree + '\'' +
                ", facultyLevel =" + facultyLevel +
                ", salary =" + salary +
                '}';
    }
}
