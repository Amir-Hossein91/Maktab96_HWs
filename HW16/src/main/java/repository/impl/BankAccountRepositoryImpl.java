package repository.impl;

import basics.repository.impl.BaseRepositoryImpl;
import entity.BankAccount;
import repository.BankAccountRepository;

import javax.persistence.Query;
import java.util.Optional;

public class BankAccountRepositoryImpl extends BaseRepositoryImpl<BankAccount> implements BankAccountRepository {
    public BankAccountRepositoryImpl(Class<BankAccount> classname) {
        super(classname);
    }

    @Override
    public Optional<BankAccount> findByCardNumber(String cardNumber) {
        String hql = "select b from BankAccount b where cardNumber=:c";
        Query query = entityManager.createQuery(hql);
        query.setParameter("c", cardNumber);
        return Optional.ofNullable((BankAccount) query.getSingleResult());
    }
}
