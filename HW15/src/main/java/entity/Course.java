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
public class Course extends BaseEntity {
    private String title;
    private int units;
    private int semesterNumber;
    @ManyToOne
    private Teacher teacher;

    public Course(String title, int units, int semesterNumber) {
        this.title = title;
        this.units = units;
        this.semesterNumber= semesterNumber;
        teacher = new Teacher();
    }
}
