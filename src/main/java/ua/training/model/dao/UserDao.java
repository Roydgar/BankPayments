package ua.training.model.dao;

import ua.training.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> login(String username, String password);

    Optional<User> findUserByLogin(String username);

    boolean userExists(String username);
}
