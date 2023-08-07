package qustion2.entity;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

@Entity
public class Student extends Person{
    private String studentCode;
    private String field;
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
                   String field,
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
                   String field,
                   int enteranceYear) {
        super(id, firstname, lastname, birthDate);
        this.studentCode = studentCode;
        this.field = field;
        this.enteranceYear = enteranceYear;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
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
                "studentCode='" + studentCode + '\'' +
                ", field='" + field + '\'' +
                ", enteranceYear=" + enteranceYear +
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
