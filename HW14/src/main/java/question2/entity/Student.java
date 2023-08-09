package question2.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
public class Student extends Person{
    @NotNull(message = "Student code can't be null")
    @Column(unique = true)
    @Digits(integer = 5,fraction = 0,message = "Student code must contain only digits")
    @Size(min = 5, max = 5, message = "Student code must have 5 digits")
    private String studentCode;
    @NotNull(message = "Field of study can't be null")
    private StudyField field;
    @Range(min = 2010,max = 2023, message = "Entrance year should be between 2010 and 2023")
    private int enteranceYear;

    public Student() {
    }

    public Student(String firstname, String lastname, String studentCode) {
        super(firstname, lastname);
        this.studentCode = studentCode;
    }

    public Student(String firstname,
                   String lastname,
                   Date birthDate,
                   String studentCode,
                   StudyField field,
                   int enteranceYear) {
        super(firstname, lastname, birthDate);
        this.studentCode = studentCode;
        this.field = field;
        this.enteranceYear = enteranceYear;
    }

    public Student(long id,
                   String firstname,
                   String lastname,
                   Date birthDate,
                   String studentCode,
                   StudyField field,
                   int enteranceYear) {
        super(id, firstname, lastname, birthDate);
        this.studentCode = studentCode;
        this.field = field;
        this.enteranceYear = enteranceYear;
    }

    public Student(String firstname, String lastname, String studentCode, StudyField field) {
        super (firstname, lastname);
        this.studentCode = studentCode;
        this.field = field;
        this.enteranceYear=2010;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public StudyField getField() {
        return field;
    }

    public void setField(StudyField field) {
        this.field = field;
    }

    public int getEnteranceYear() {
        return enteranceYear;
    }

    public void setEnteranceYear(int enteranceYear) {
        this.enteranceYear = enteranceYear;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id =" + getId() +
                ", firstname ='" + getFirstname() + '\'' +
                ", lastname ='" + getLastname() + '\'' +
                ", birthDate ='" + getBirthDate() + '\'' +
                ", studentCode ='" + studentCode + '\'' +
                ", field ='" + field + '\'' +
                ", enteranceYear =" + enteranceYear +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return enteranceYear == student.enteranceYear && studentCode.equals(student.studentCode) && Objects.equals(field, student.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studentCode, field, enteranceYear);
    }
}
