package ua.training.util.constants;

import java.util.ResourceBundle;

public interface AccountConstants {
    ResourceBundle moneyBundle = ResourceBundle.getBundle(PropertyFileNames.MONEY);

    Long CHECKING_BALANCE_LIMIT = Long.parseLong(moneyBundle.getString("balance.limit.checking"));
    Long CREDIT_BALANCE_LIMIT   = Long.parseLong(moneyBundle.getString("balance.limit.credit"));
    Long DEPOSIT_BALANCE_LIMIT   = Long.parseLong(moneyBundle.getString("balance.limit.deposit"));

    Integer VALIDITY_TIME       = Integer.parseInt(moneyBundle.getString("validity.timeInMonth"));
}

