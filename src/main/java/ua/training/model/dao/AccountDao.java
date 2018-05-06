package ua.training.model.dao;

import org.javamoney.moneta.Money;
import ua.training.model.entity.Account;

public interface AccountDao extends GenericDao<Account> {
    void updateBalance(int accountId, Money balance);
    void updateAccruedInterest(int accountId,double accruedInterest);
}