package ua.training.model.dao.impl;

import ua.training.model.dao.UserHasAccountDao;
import ua.training.model.dao.impl.constants.UserHasAccountQueries;
import ua.training.model.dao.util.ExtractUtil;
import ua.training.model.entity.Account;
import ua.training.model.entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserHasAccountDao implements UserHasAccountDao {

    private Connection connection;

    JDBCUserHasAccountDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(int userId, int accountId) {
        try (PreparedStatement ps = connection.prepareStatement
                (UserHasAccountQueries.CREATE)){
            ps.setInt(1, userId);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> findAccountsByUserId(int userId) {
        List<Account> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement
                (UserHasAccountQueries.FIND_ACCOUNTS_BY_USER_ID)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while ( rs.next() ){
                resultList.add(ExtractUtil.extractAccountFromResultSet(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public List<User> findUsersByAccountId(int accountId) {
        List<User> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement
                (UserHasAccountQueries.FIND_USERS_BY_ACCOUNT_ID)) {
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            while ( rs.next() ){
                resultList.add(ExtractUtil.extractUserFromResultSet(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public boolean exists (int userId, int accountId) {
        List<User> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement
                (UserHasAccountQueries.FIND_BY_USER_AND_ACCOUNT_ID)) {
            ps.setInt(1, userId);
            ps.setInt(2, accountId);
            ResultSet rs = ps.executeQuery();

            return rs.next();
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
