package entity;


import entity.baseEntity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@SequenceGenerator(name = "idGenerator", sequenceName = "courseSequence")
public class Course extends BaseEntity {
    @NotNull(message = "the course title can not be null!")
    private String title;
    @NotNull(message = "Specify the ")
    private int credits;
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
