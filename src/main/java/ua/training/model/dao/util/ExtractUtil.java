package ua.training.model.dao.util;

import ua.training.model.dao.impl.constants.ColumnNames;
import ua.training.model.entity.Account;
import ua.training.model.entity.User;
import ua.training.util.AccountUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ExtractUtil {

    public static Account extractAccountFromResultSet(ResultSet rs) throws SQLException {
        int accountId = rs.getInt(ColumnNames.ACCOUNT_ID);
        String number = rs.getString(ColumnNames.ACCOUNT_NUMBER);
        long balance  = rs.getLong(ColumnNames.ACCOUNT_BALANCE);
        LocalDateTime creationDate = rs.getTimestamp(ColumnNames.ACCOUNT_CREATION_DATE).toLocalDateTime();
        LocalDateTime validityDate = rs.getTimestamp(ColumnNames.ACCOUNT_VALIDITY_DATE).toLocalDateTime();
        double rate = rs.getBigDecimal(ColumnNames.ACCOUNT_RATE).doubleValue();
        double accruedInterest = rs.getBigDecimal(ColumnNames.ACCOUNT_ACCRUED_INTEREST).doubleValue();
        long limit = rs.getLong(ColumnNames.ACCOUNT_LIMIT);
        Account.Type accountType = Account.Type.valueOf(rs.getString(ColumnNames.ACCOUNT_TYPE).toUpperCase());

        return new Account.AccountBuilder().setId(accountId).setNumber(number)
                .setBalance(AccountUtil.getMoneyInDefaultCurrency(balance)).setCreationTDate(creationDate)
                .setValidityDate(validityDate).setRate(rate).setAccruedInterest(accruedInterest)
                .setBalanceLimit(AccountUtil.getMoneyInDefaultCurrency(limit)).setType(accountType).create();
    }

    public static User extractUserFromResultSet(ResultSet rs)
            throws SQLException {
        int userId       =  rs.getInt(ColumnNames.USER_ID);
        String login  =  rs.getString(ColumnNames.USER_LOGIN);
        String email     = rs.getString(ColumnNames.USER_EMAIL);
        String password  =  rs.getString(ColumnNames.USER_PASSWORD);
        String role      =  rs.getString(ColumnNames.USER_ROLE);
        return new User.UserBuilder().setId(userId).setLogin(login).setEmail(email).setPassword(password)
                .setRole(User.Role.valueOf(role.toUpperCase())).create();
    }

}
