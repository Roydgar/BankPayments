package ua.training;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import ua.training.model.dao.*;
import ua.training.model.entity.CreditRequest;
import ua.training.model.entity.User;
import ua.training.util.AccountUtil;

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
        UserDao userDao = DaoFactory.getInstance().createUserDao();
        userDao.create(new User.UserBuilder().setLogin("roydgar").setPassword(DigestUtils.md5Hex("VS824"))
                .setEmail("Royd@gmail.com").setRole(User.Role.ADMIN).create());
    }

}

