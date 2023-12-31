package entity;


import entity.baseEntity.BaseEntity;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Score extends BaseEntity {
    @ManyToOne
    private Course course;
    @ManyToOne
    private Student student;
    @Range(min = 0, max = 20, message = "Score value must be between 0 to 20")
    private Float value;
    private boolean isPassed;

    public Score(Course course, Student student) {
        this.course = course;
        this.student = student;
        value = null;
        isPassed = false;
    }

    public String toString() {
        return super.toString() +
                ", course = [" + this.getCourse() + "]" +
                ", student = [" + this.getStudent() + "]" +
                ", value = " + this.getValue() +
                ", isPassed = " + this.isPassed();
    }
}
