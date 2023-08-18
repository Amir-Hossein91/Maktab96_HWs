package entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Course course;
    @ManyToOne
    private Student student;
    private int semesterNumber;
    private Float value;
    private boolean isPassed;

    public Score(Course course, Student student, int semesterNumber, Float value, boolean isPassed) {
        this.course = course;
        this.student = student;
        this.semesterNumber = semesterNumber;
        this.value = value;
        this.isPassed = isPassed;
    }
}
