package entity;


import entity.baseEntity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


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
    @Pattern(regexp = "^[a-zA-Z\s]{3,}$",message = "Invalid name format")
    @NotNull(message = "Specify the name")
    private String firstname;
    @Pattern(regexp = "^[a-zA-Z\s]{3,}$",message = "Invalid last name format")
    @NotNull(message = "Specify the last name")
    private String lastname;
    @Pattern(regexp = "[0-9]{10}", message = "Invalid national code number")
    @NotNull(message = "Specify the national code")
    @Column(unique = true)
    private String nationalCode;
    @Pattern(regexp = "09[0-9]{9}", message = "Invalid phone number format")
    @Column(unique = true)
    private String phoneNumber;
    @Pattern(regexp = "^[^\s]{3,}$", message = "Username can not contain character 'space'")
    @Column(unique = true)
    @NotNull(message = "Specify a valid username")
    private String username;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$",
    message = "Password can not be less than 6 characters and must contain at least one lowerCase, one upperCase and " +
            "one digit characters")
    @NotNull(message = "Specify a valid password")
    private String password;
    @Pattern(regexp = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$",
    message = "Invalid email address format")
    @Column(unique = true)
    private String email;

}
