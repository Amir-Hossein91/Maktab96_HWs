package entity;

import entity.base.BaseEntity;
import entity.enums.Bank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;
import utility.Constants;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

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
    @Enumerated(value = EnumType.STRING)
    private Bank bank;
    @Pattern(regexp = "^[0-9]{16}$", message = Constants.INVALID_CARD_NUMBER_EXCEPTION)
    private String cardNumber;
    @Pattern(regexp = "^[0-9]{3,4}$", message = Constants.INVALID_CVV2_NUMBER_EXCEPTION)
    private String cvv2;
    @Range(min = 1, max = 12, message = Constants.INVALID_EXPIRATION_MONTH_EXCEPTION)
    private int expirationMonth;
    @Pattern(regexp = "^[1-2][0-9]{3}$" , message = Constants.INVALID_EXPIRATION_YEAR_EXCEPTION)
    private int expirationYear;
    @OneToOne(mappedBy = "bankAccount")
    private Student owner;
    @Range(min = 0)
    private Long balance;
}
