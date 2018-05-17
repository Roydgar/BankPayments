package ua.training.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entity.Account;
import ua.training.model.entity.User;

import java.util.List;

public interface UserHasAccountDao extends AutoCloseable{
    Logger logger = LogManager.getRootLogger();

    void create(int userId, int accountId);
    List<Account> findAccountsByUserId(int userId);
    List<User> findUsersByAccountId(int accountId);
    boolean exists (int userId, int accountId);
}
