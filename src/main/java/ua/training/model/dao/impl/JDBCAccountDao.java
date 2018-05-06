package ua.training.model.dao.impl;

import org.javamoney.moneta.Money;
import ua.training.exception.NoResultFromDbException;
import ua.training.model.dao.AccountDao;
import ua.training.model.dao.impl.constants.AccountQueries;
import ua.training.model.dao.impl.constants.ColumnNames;
import ua.training.model.entity.Account;
import ua.training.util.AccountUtil;
import ua.training.util.constants.ExceptionMessages;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JDBCAccountDao implements AccountDao{

    private Connection connection;

    JDBCAccountDao(Connection connection) {
        this.connection = connection;
    }

    private void prepareCreateUpdateQuery(Account account, PreparedStatement ps) throws SQLException {
        ps.setString(1 , account.getNumber());
        ps.setLong(2, account.getBalance().getNumber().longValue());
        ps.setTimestamp(3, Timestamp.valueOf(account.getCreationDate()));
        ps.setTimestamp(4, Timestamp.valueOf(account.getValidityDate()));
        ps.setBigDecimal(5, new BigDecimal(account.getRate()));
        ps.setBigDecimal(6, new BigDecimal(account.getAccruedInterest()));
        ps.setLong(7, account.getBalanceLimit().getNumber().longValue());
        ps.setString(8, account.getType().toString());
    }

    @Override
    public void updateBalance(int accountId, Money balance) {
        try (PreparedStatement ps = connection.prepareStatement(
                AccountQueries.UPDATE_BALANCE)){
            ps.setLong(1, balance.getNumber().longValue());
            ps.setInt(2, accountId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccruedInterest(int accountId, double accruedInterest) {
        try (PreparedStatement ps = connection.prepareStatement(
                AccountQueries.UPDATE_ACCRUED_INTEREST)){
            ps.setDouble(1, accruedInterest);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Account entity) {
        try (PreparedStatement ps = connection.prepareStatement
                (AccountQueries.CREATE)){

            prepareCreateUpdateQuery(entity, ps);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findById(int id) throws NoResultFromDbException {
        try (PreparedStatement ps = connection.prepareStatement
                (AccountQueries.FIND_BY_ID)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                return extractFromResultSet(rs);
            } else {
                throw new NoResultFromDbException(ExceptionMessages.NO_RESULT_FROM_DB);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> findAll() {
        List<Account> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()){
            ResultSet rs = ps.executeQuery(AccountQueries.FIND_ALL);

            while ( rs.next() ){
                resultList.add(extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    private Account extractFromResultSet(ResultSet rs) throws SQLException{
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

    @Override
    public void update(Account entity) {
        try (PreparedStatement ps = connection.prepareStatement(
                AccountQueries.UPDATE)){
            prepareCreateUpdateQuery(entity, ps);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement(AccountQueries.DELETE)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}