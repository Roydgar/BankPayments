package ua.training.util.constants;

import java.util.ResourceBundle;

public interface AccountConstants {
    ResourceBundle moneyBundle = ResourceBundle.getBundle(PropertyFileNames.MONEY);

    Long CHECKING_BALANCE_LIMIT   = Long.parseLong(moneyBundle.getString("checking.limit"));
    Long CREDIT_BALANCE_LIMIT     = Long.parseLong(moneyBundle.getString("credit.limit"));
    Long DEPOSIT_BALANCE_LIMIT    = Long.parseLong(moneyBundle.getString("deposit.limit"));
    Long CHECKING_INITIAL_BALANCE = Long.parseLong(moneyBundle.getString("checking.InitialBalance"));

    Double CREDIT_RATE            = Double.parseDouble(moneyBundle.getString("credit.rate"));
    Double DEPOSIT_RATE           = Double.parseDouble(moneyBundle.getString("deposit.rate"));

    Integer VALIDITY_TIME         = Integer.parseInt(moneyBundle.getString("validity.time"));

}

