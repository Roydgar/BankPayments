package ua.training.model.service;

import org.javamoney.moneta.Money;
import ua.training.exception.NoResultFromDbException;
import ua.training.model.dao.AccountDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserHasAccountDao;
import ua.training.model.entity.Account;
import ua.training.util.AccountUtil;

import java.time.LocalDateTime;
import java.util.List;

public class AccountService {

    private AccountDao accountDao = DaoFactory.getInstance().createAccountDao();
    private UserHasAccountDao userHasAccountDao = DaoFactory.getInstance().createUserHasAccountDao();

    public void updateBalance(int accountId, Money balance) {
        accountDao.updateBalance(accountId, balance);
    }

    public void updateAccruedInterest(int accountId, double accruedInterest) {
        accountDao.updateAccruedInterest(accountId, accruedInterest);
    }

    public void create(String type, int userId) {
        Account.Type accountType = Account.Type.valueOf(type.toUpperCase());
        LocalDateTime creationTime = LocalDateTime.now();
        String generatedNumber = AccountUtil.generateAccountNumber();

        Account account = new Account.AccountBuilder().setNumber(generatedNumber)
                .setBalance(AccountUtil.getZeroBalance()).setAccruedInterest(0).setRate(0)
                .setBalanceLimit(AccountUtil.getBalanceLimit(accountType)).setCreationTDate(creationTime )
                .setValidityDate(AccountUtil.generateValidityTime(creationTime ))
                .setType(accountType).create();
        accountDao.create(account);

        try {
            userHasAccountDao.create(userId, accountDao.findByNumber(generatedNumber).getId());
        } catch (NoResultFromDbException e) {
            throw new RuntimeException(e);
        }
    }

    public Account findById(int id) throws NoResultFromDbException {
        return accountDao.findById(id);
    }

    public List<Account> findAll() {
        return accountDao.findAll();
    }

    public void update(Account entity) {
        accountDao.update(entity);
    }

    public void delete(int id) {
        accountDao.delete(id);
    }

    public List<Account> findAccountsByUserId(int userId) {
        return userHasAccountDao.findAccountsByUserId(userId);
    }
}
