package ua.training;

import org.junit.Test;
import ua.training.model.dao.AccountDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.UserHasAccountDao;
import ua.training.model.entity.Account;
import ua.training.util.AccountUtil;

import java.time.LocalDateTime;

public class DatabaseTest {
    @Test
    public void testLogin() {
        AccountDao dao = DaoFactory.getInstance().createAccountDao();

        LocalDateTime when = LocalDateTime.now();

        dao.create(new Account.AccountBuilder().setNumber(AccountUtil.generateAccountNumber())
        .setBalance(AccountUtil.getZeroBalance()).setAccruedInterest(0).setRate(0)
        .setBalanceLimit(AccountUtil.getBalanceLimit(Account.Type.CHECKING)).setCreationTDate(when)
        .setValidityDate(AccountUtil.generateValidityTime(when)).setType(Account.Type.CHECKING).create());

        System.out.println(dao.findAll());

    }

    @Test
    public void test() {
        UserHasAccountDao dao = DaoFactory.getInstance().createUserHasAccountDao();
        System.out.println(dao.findUsersByAccountId(2));
    }

    @Test
    public void testFindAll(){
        AccountDao accountDao = DaoFactory.getInstance().createAccountDao();
        UserDao userDao = DaoFactory.getInstance().createUserDao();

        System.out.println(accountDao.findAll());
        System.out.println(userDao.findAll());
    }

}

