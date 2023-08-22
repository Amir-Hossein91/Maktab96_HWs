package entity;


import entity.baseEntity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;
import utility.Constants;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@SequenceGenerator(name = "idGenerator", sequenceName = "courseSequence")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"title","credits","semesterNumber"})})
public class Course extends BaseEntity {
    @NotNull(message = "the course title can not be null!")
    private String title;
    @NotNull(message = "Specify the credits of the course!")
    @Range(min = 1, max = 6, message = "course credits can not be less than 1 and more than 6")
    private int credits;
    @NotNull(message = "Specify the semester in which the course is being presented!")
    @Range(min = 1, max = Constants.CURRENT_SEMESTER_NUMBER, message = "Invalid semester number")
    private int semesterNumber;

    public String toString() {
        return super.toString() +
                ", title = " + this.getTitle() +
                ", credits = " + this.getCredits() +
                ", semesterNumber = " + this.getSemesterNumber();
    }
}
