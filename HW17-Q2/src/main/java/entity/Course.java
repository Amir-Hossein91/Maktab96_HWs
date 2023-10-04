package entity;

import entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "Generator", sequenceName = "Course_Sequence")
public class Course extends BaseEntity {
    @Pattern(regexp = "^[^,]+$", message = "Course name can not be empty or contain ',' ")
    private String name;
}
