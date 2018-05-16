package ua.training.model.dao.util;

import org.javamoney.moneta.Money;
import ua.training.model.dao.impl.constants.ColumnNames;
import ua.training.model.entity.Account;
import ua.training.model.entity.CreditRequest;
import ua.training.model.entity.Operation;
import ua.training.model.entity.User;
import ua.training.util.AccountUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ExtractUtil {

    public static Account extractAccountFromResultSet(ResultSet rs) throws SQLException {
        int accountId = rs.getInt(ColumnNames.ACCOUNT_ID);
        String number = rs.getString(ColumnNames.ACCOUNT_NUMBER);
        Money balance  = AccountUtil.getMoneyInDefaultCurrency(rs.getLong(ColumnNames.ACCOUNT_BALANCE));
        LocalDateTime creationDate = rs.getTimestamp(ColumnNames.ACCOUNT_CREATION_DATE).toLocalDateTime();
        LocalDateTime validityDate = rs.getTimestamp(ColumnNames.ACCOUNT_VALIDITY_DATE).toLocalDateTime();
        double rate = rs.getBigDecimal(ColumnNames.ACCOUNT_RATE).doubleValue();
        double accruedInterest = rs.getBigDecimal(ColumnNames.ACCOUNT_ACCRUED_INTEREST).doubleValue();
        long limit = rs.getLong(ColumnNames.ACCOUNT_LIMIT);
        Account.Type accountType = Account.Type.valueOf(rs.getString(ColumnNames.ACCOUNT_TYPE).toUpperCase());

        return new Account.AccountBuilder().setId(accountId).setNumber(number)
                .setBalance(balance).setCreationTDate(creationDate)
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

    public static Operation extractOperationFromResultSet(ResultSet rs)
            throws SQLException {
        int operationId = rs.getInt(ColumnNames.OPERATION_ID);
        int accountId   = rs.getInt(ColumnNames.ACCOUNT_ID);
        String recipient = rs.getString(ColumnNames.OPERATION_RECIPIENT);
        Operation.Type type = Operation.Type.valueOf(rs.getString(ColumnNames.OPERATION_TYPE));
        LocalDateTime date = rs.getTimestamp(ColumnNames.OPERATION_DATE).toLocalDateTime();
        Money moneyAmount = AccountUtil.getMoneyInDefaultCurrency(rs.getLong(ColumnNames.OPERATION_MONEY_AMOUNT));

        return new Operation.OperationBuilder().setId(operationId).setAccountId(accountId).setRecipient(recipient)
                .setType(type).setDate(date).setMoneyAmount(moneyAmount).create();
    }

    public static CreditRequest extractCreditRequestFromResultSet(ResultSet rs)
            throws SQLException {
        int id = rs.getInt(ColumnNames.CREDIT_REQUEST_ID);
        int userId = rs.getInt(ColumnNames.USER_ID);
        Money moneyAmount = AccountUtil.getMoneyInDefaultCurrency(rs.getLong(ColumnNames.CREDIT_REQUEST_MONEY_AMOUNT));
        LocalDateTime date = rs.getTimestamp(ColumnNames.CREDIT_REQUEST_DATE).toLocalDateTime();
        CreditRequest.Status status = CreditRequest.Status.valueOf(rs.getString(ColumnNames.CREDIT_REQUEST_STATUS));

        return new CreditRequest.CreditRequestBuilder().setId(id).setUserId(userId)
                .setDate(date).setMoneyAmount(moneyAmount).setStatus(status).create();
    }
}
