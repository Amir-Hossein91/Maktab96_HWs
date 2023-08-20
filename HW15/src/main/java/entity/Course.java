package entity;


import entity.baseEntity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;
import utility.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@SuperBuilder
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
    @NotNull(message = "Specify the credits of the course!")
    @Range(min = 1, max = 6, message = "course credits can not be less than 1 and more than 6")
    private int credits;
    @NotNull(message = "Specify the semester in which the course is being presented!")
    @Range(min = 1, max = Constants.CURRENT_SEMESTER_NUMBER, message = "Invalid semester number")
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
