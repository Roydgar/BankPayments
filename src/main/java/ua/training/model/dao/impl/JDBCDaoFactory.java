package ua.training.model.dao.impl;

import ua.training.model.dao.AccountDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.UserHasAccountDao;

import java.sql.SQLException;

import static ua.training.model.dao.util.ConnectionUtils.getConnection;

public class JDBCDaoFactory extends DaoFactory {
    @Override
    public UserDao createUserDao() {
        try {
            return new JDBCUserDao(getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AccountDao createAccountDao() {
        try {
            return new JDBCAccountDao(getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserHasAccountDao createUserHasAccountDao() {
        try {
            return new JDBCUserHasAccountDao(getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
