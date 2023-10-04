package entity;

import entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseRating extends BaseEntity {
    private Date timeStamp;
    private float rate;
    private String comment;
}
