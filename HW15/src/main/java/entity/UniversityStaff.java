package entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@SuperBuilder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UniversityStaff extends Person{

    private long totalSalary;

    public UniversityStaff(String firstname, String lastname, String nationalCode,
                           String phoneNumber, String username, String password, String email) {
        super(firstname, lastname, nationalCode, phoneNumber, username, password, email);
    }
}
