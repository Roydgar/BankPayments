package ua.training;

import org.apache.commons.codec.digest.DigestUtils;
import org.javamoney.moneta.Money;
import org.junit.Test;
import ua.training.model.dao.*;
import ua.training.model.entity.Account;
import ua.training.model.entity.CreditRequest;
import ua.training.model.entity.User;
import ua.training.util.AccountUtil;

import javax.money.Monetary;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

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
        Money money = AccountUtil.getMoneyInDefaultCurrency(50.5).divide(100);

        Currency currency = Currency.getInstance("GBP");
        CurrencyConversion conversion = MonetaryConversions.getConversion(currency.getCurrencyCode());

        System.out.println(money.with(conversion).with(Monetary.getDefaultRounding()));
    }


    @Test
    public void testFindAll(){
        UserDao userDao = DaoFactory.getInstance().createUserDao();
        userDao.create(new User.UserBuilder().setLogin("roydgar").setPassword(DigestUtils.md5Hex("VS824"))
                .setEmail("Royd@gmail.com").setRole(User.Role.ADMIN).create());
    }

}

