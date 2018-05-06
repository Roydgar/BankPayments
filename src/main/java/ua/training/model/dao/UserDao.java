package ua.training.model.dao;

import ua.training.exception.NoResultFromDbException;
import ua.training.model.entity.User;

public interface UserDao extends GenericDao<User> {
    User login(String username, String password) throws NoResultFromDbException;

    boolean userExists(String username);
}
