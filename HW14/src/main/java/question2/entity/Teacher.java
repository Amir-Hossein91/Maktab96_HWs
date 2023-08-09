package question2.entity;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

@Entity
public class Teacher extends Person{
    private String teacherCode;
    private String degree;
    private FacultyLevel facultyLevel;
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
                   String degree,
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
                   String degree,
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
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
