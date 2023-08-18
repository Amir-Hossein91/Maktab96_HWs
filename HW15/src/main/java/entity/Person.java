package entity;


import entity.baseEntity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Person_Role" , discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("no role")
public class Person extends BaseEntity {
    private String firstname;
    private String lastname;
    private String nationalCode;
    private String phoneNumber;
    private String username;
    private String password;
    private String email;

}
