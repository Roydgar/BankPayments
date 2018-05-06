package ua.training.model.dao;

import ua.training.exception.UserDoesntExistException;
import ua.training.model.entity.User;

public interface UserDao extends GenericDao<User> {
    User login(String username, String password) throws UserDoesntExistException;

    boolean userExists(String username);
}
