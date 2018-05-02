package ua.training.model.dao;

import ua.training.model.entity.User;

public interface UserDao extends GenericDao<User> {
    User login(String username, String password);

    boolean userExists(String username);
}
