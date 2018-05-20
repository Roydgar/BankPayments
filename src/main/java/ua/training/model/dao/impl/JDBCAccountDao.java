package ua.training.model.dao.impl;

import org.javamoney.moneta.Money;
import ua.training.model.dao.AccountDao;
import ua.training.model.dao.impl.constants.AccountQueries;
import ua.training.model.dao.util.ExtractUtil;
import ua.training.model.entity.Account;
import ua.training.util.LoggerMessageUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            logger.error(LoggerMessageUtil.daoException(), e);
            try { connection.rollback(); } catch (SQLException exc) {
                logger.error(LoggerMessageUtil.daoException(), e);
                throw new RuntimeException(e);
            }
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
            logger.error(LoggerMessageUtil.daoException(), e);
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
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Account> findById(int id){
        Optional<Account> account = Optional.empty();

        try (PreparedStatement ps = connection.prepareStatement
                (AccountQueries.FIND_BY_ID)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                account = Optional.of(ExtractUtil.extractAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
        return account;
    }

    @Override
    public Optional<Account> findByNumber(String number){
        Optional<Account> account = Optional.empty();

        try (PreparedStatement ps = connection.prepareStatement
                (AccountQueries.FIND_BY_NUMBER)){
            ps.setString(1, number);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                account = Optional.of(ExtractUtil.extractAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
        return account;
    }

    @Override
    public List<Account> findAll() {
        List<Account> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()){
            ResultSet rs = ps.executeQuery(AccountQueries.FIND_ALL);

            while ( rs.next() ){
                resultList.add(ExtractUtil.extractAccountFromResultSet(rs));
            }

        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
        return resultList;
    }



    @Override
    public void update(Account entity) {
        try (PreparedStatement ps = connection.prepareStatement(
                AccountQueries.UPDATE)){
            prepareCreateUpdateQuery(entity, ps);
            ps.setInt(9, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement(AccountQueries.DELETE)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (Exception e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setAutocommit(boolean autocommit) {
        try {
            if (autocommit) {
                connection.commit();
            }
            connection.setAutoCommit(autocommit);
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }
}
