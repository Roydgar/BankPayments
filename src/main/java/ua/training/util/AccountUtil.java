package ua.training.util;

import org.javamoney.moneta.Money;
import ua.training.model.entity.Account;
import ua.training.util.constants.AccountConstants;
import ua.training.util.constants.GlobalConstants;

import java.time.LocalDateTime;

public class AccountUtil {

    public static Money getMoneyInDefaultCurrency(long money) {
        return Money.of(money, GlobalConstants.DEFAULT_CURRENCY);
    }

    public static Money getMoneyInDefaultCurrency(double money) {
        return Money.of(money, GlobalConstants.DEFAULT_CURRENCY);
    }

    public static Money getMoneyInDefaultCurrency(String money) {
        return Money.of(Double.parseDouble(money), GlobalConstants.DEFAULT_CURRENCY);
    }

    public static Money getZeroBalance() {
        return getMoneyInDefaultCurrency(0);
    }

    public static Money getInitialCheckingBalance() {
        return getMoneyInDefaultCurrency(AccountConstants.CHECKING_INITIAL_BALANCE);
    }

    public static Money getBalanceLimit(Account.Type type) {
        if (type == Account.Type.CREDIT) {
            return getMoneyInDefaultCurrency(AccountConstants.CREDIT_BALANCE_LIMIT);
        } else if(type == Account.Type.DEPOSIT) {
            return getMoneyInDefaultCurrency(AccountConstants.DEPOSIT_BALANCE_LIMIT);
        } else {
            return getMoneyInDefaultCurrency(AccountConstants.CHECKING_BALANCE_LIMIT);
        }
    }

    public static double getRate(Account.Type type) {
        if (type == Account.Type.CREDIT) {
            return AccountConstants.CREDIT_RATE;
        } else if(type == Account.Type.DEPOSIT) {
            return AccountConstants.DEPOSIT_RATE;
        } else {
            return 0.0;
        }
    }

    public static boolean moneyIsBiggerThanLimit(Money money, Account.Type type) {
        return money.compareTo(getBalanceLimit(type)) > 0;
    }

    public static String generateAccountNumber() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            builder.append(random(1000, 9999));
        }
        return builder.toString();
    }

    public static LocalDateTime generateValidityTime(LocalDateTime creationTime) {
        return creationTime.plusMonths(AccountConstants.VALIDITY_TIME);
    }

    private static int random(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
