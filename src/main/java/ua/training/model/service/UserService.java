package ua.training.model.service;

import org.apache.commons.codec.digest.DigestUtils;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDao userDao = DaoFactory.getInstance().createUserDao();

    public void create(String login, String password, User.Role role, String email) {
        userDao.create(new User.UserBuilder().setLogin(login).setPassword(DigestUtils.md5Hex(password))
        .setEmail(email).setRole(role).create());
    }

    public User findById(int id) {
        return null;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public void update(User entity) {
        userDao.update(entity);
    }

    public void delete(int id) {
        userDao.delete(id);
    }

    public Optional<User> login(String login, String password){
        return userDao.login(login, DigestUtils.md5Hex(password));
    }

    public boolean userExists(String login) {
        return userDao.userExists(login);
    }

    public Optional<User> getUserByLogin(String login){
        return userDao.findUserByLogin(login);
    }

    public boolean userIsLogged(HttpServletRequest request, String login) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");

        if(loggedUsers.stream().anyMatch(login::equals)){
            return true;
        }

        loggedUsers.add(login);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }

}
