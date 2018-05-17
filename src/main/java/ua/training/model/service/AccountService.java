package ua.training.model.service;

import org.javamoney.moneta.Money;
import ua.training.model.dao.AccountDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserHasAccountDao;
import ua.training.model.entity.Account;
import ua.training.util.AccountUtil;
import ua.training.util.ConvertUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AccountService {

    private AccountDao accountDao = DaoFactory.getInstance().createAccountDao();
    private UserHasAccountDao userHasAccountDao = DaoFactory.getInstance().createUserHasAccountDao();

    public void updateBalance(int accountId, Money balance) {
        accountDao.updateBalance(accountId, ConvertUtil.convertDollarsToCents(balance));
    }

    public void updateAccruedInterest(int accountId, double accruedInterest) {
        accountDao.updateAccruedInterest(accountId, accruedInterest);
    }

    public void create(Account.Type accountType, int userId, Money moneyAmount) {
        LocalDateTime creationTime = LocalDateTime.now();
        String generatedNumber = AccountUtil.generateAccountNumber();
        double rate = AccountUtil.getRate(accountType);

        Account account = new Account.AccountBuilder().setNumber(generatedNumber)
                .setBalance(ConvertUtil.convertDollarsToCents(moneyAmount)).setAccruedInterest(0).setRate(rate)
                .setBalanceLimit(ConvertUtil.convertDollarsToCents(AccountUtil.getBalanceLimit(accountType)))
                .setCreationTDate(creationTime).setValidityDate(AccountUtil.generateValidityTime(creationTime))
                .setType(accountType).create();
        accountDao.create(account);

        userHasAccountDao.create(userId, accountDao.findByNumber(generatedNumber).get().getId());

    }

    public void create(Account.Type accountType, int userId) {
        create(accountType, userId, AccountUtil.getInitialCheckingBalance());

    }

    public Optional<Account> findById(int id){
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

    public void addUserToAccount(int userId, int accountId) {
        if (!userHasAccountDao.exists(userId, accountId)) {
            userHasAccountDao.create(userId, accountId);
        }
    }

    public Optional<Account> findByNumber(String number){
        return accountDao.findByNumber(number);
    }

}
