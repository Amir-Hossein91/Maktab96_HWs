package entity;


import entity.baseEntity.BaseEntity;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Score extends BaseEntity {
    @ManyToOne/*(cascade = CascadeType.PERSIST)*/
    private Course course;
    @ManyToOne/*(cascade = CascadeType.PERSIST)*/
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
}
