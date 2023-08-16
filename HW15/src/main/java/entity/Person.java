package entity;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Role" , discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    private String firstname;
    private String lastname;
    private String nationalCode;
    private String phoneNumber;
    private String email;
}
