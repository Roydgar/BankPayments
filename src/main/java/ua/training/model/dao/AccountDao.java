package ua.training.model.dao;

import org.javamoney.moneta.Money;
import ua.training.model.entity.Account;

import java.util.Optional;

public interface AccountDao extends GenericDao<Account> {
    void updateBalance(int accountId, Money balance);
    void updateAccruedInterest(int accountId,double accruedInterest);
    Optional<Account> findByNumber(String number);
    void setAutocommit(boolean autocommit);
}
