package entity;

import entity.base.BaseEntity;
import entity.enums.Bank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@SequenceGenerator(name = "idGenerator" , sequenceName = "bankAccount_Sequence")
public class BankAccount extends BaseEntity {
    private Bank bank;
    private String cardNumber;
    private String cvv2;
    private String expirationMonth;
    private String expirationYear;
    @OneToOne
    private Student owner;
    private Long balance;
}
