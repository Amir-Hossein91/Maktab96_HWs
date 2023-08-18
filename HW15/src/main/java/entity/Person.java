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
@DiscriminatorColumn(name = "Person_Role" , discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("no role")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    private String firstname;
    private String lastname;
    private String nationalCode;
    private String phoneNumber;
    private String email;

    public Person(String firstname, String lastname, String nationalCode, String phoneNumber, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationalCode = nationalCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
