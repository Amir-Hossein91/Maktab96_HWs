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
@SequenceGenerator(name = "idGenerator", sequenceName = "courseSequence")
public class Course extends BaseEntity {
    private String title;
    private int units;
    private int semesterNumber;
//    @ManyToOne/*(cascade = CascadeType.MERGE)*/
//    private Teacher teacher;

//    public Course(String title, int units, int semesterNumber) {
//        this.title = title;
//        this.units = units;
//        this.semesterNumber= semesterNumber;
//        teacher = new Teacher();
//    }
}
