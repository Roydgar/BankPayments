package ua.training.model.dao.impl;

import ua.training.model.dao.*;
import ua.training.util.LoggerMessageUtil;

import java.sql.SQLException;

import static ua.training.model.dao.util.ConnectionUtils.getConnection;

public class JDBCDaoFactory extends DaoFactory {
    @Override
    public UserDao createUserDao() {
        try {
            return new JDBCUserDao(getConnection());
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public AccountDao createAccountDao() {
        try {
            return new JDBCAccountDao(getConnection());
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserHasAccountDao createUserHasAccountDao() {
        try {
            return new JDBCUserHasAccountDao(getConnection());
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public OperationDao createOperationDao() {
        try {
            return new JDBCOperationDao(getConnection());
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public CreditRequestDao createCreditRequestDao() {
        try {
            return new JDBCCreditRequestDao(getConnection());
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }
}
