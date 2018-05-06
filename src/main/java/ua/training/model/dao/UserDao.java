package ua.training.model.dao;

import ua.training.exception.LoginFailedException;
import ua.training.model.entity.User;

public interface UserDao extends GenericDao<User> {
    User login(String username, String password) throws LoginFailedException;

    boolean userExists(String username);
}
