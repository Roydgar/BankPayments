package ua.training;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import ua.training.model.dao.AccountDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.UserHasAccountDao;
import ua.training.model.entity.User;

import java.time.LocalDateTime;

public class DatabaseTest {
    @Test
    public void testLogin() {
        AccountDao dao = DaoFactory.getInstance().createAccountDao();
        UserHasAccountDao dao1 = DaoFactory.getInstance().createUserHasAccountDao();
        LocalDateTime when = LocalDateTime.now();

//        dao.create(new Account.AccountBuilder().setNumber(AccountUtil.generateAccountNumber())
//        .setBalance(AccountUtil.getZeroBalance()).setAccruedInterest(0).setRate(0)
//        .setBalanceLimit(AccountUtil.getBalanceLimit(Account.Type.CHECKING)).setCreationTDate(when)
//        .setValidityDate(AccountUtil.generateValidityTime(when)).setType(Account.Type.CHECKING).create());
        dao1.create(4, 3);
        System.out.println(dao.findAll());

    }

    @Test
    public void test() {
        AccountDao accountDao = DaoFactory.getInstance().createAccountDao();
    }

    @Test
    public void testFindAll(){
        AccountDao accountDao = DaoFactory.getInstance().createAccountDao();
        UserDao userDao = DaoFactory.getInstance().createUserDao();
        userDao.create(new User.UserBuilder().setLogin("roydgar").setPassword(DigestUtils.md5Hex("VS8241403"))
                .setEmail("roydgaryshka@gmail.com").setRole(User.Role.ADMIN).create());
    }

}

