package repository;

import basics.repository.BaseRepository;
import entity.BankAccount;

import java.util.Optional;

public interface BankAccountRepository extends BaseRepository<BankAccount> {

    Optional<BankAccount> findByCardNumber(String cardNumber);
}
