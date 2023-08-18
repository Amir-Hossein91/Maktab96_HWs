package entity;


import entity.baseEntity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Score extends BaseEntity {
    @ManyToOne
    private Course course;
    @ManyToOne
    private Student student;
    private Float value;
    private boolean isPassed;

    public Score(Course course, Student student, Float value) {
        this.course = course;
        this.student = student;
        this.value = value;
        if(value >= 10)
            isPassed = true;
    }
}
