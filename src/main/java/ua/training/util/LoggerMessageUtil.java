package ua.training.util;

import ua.training.model.entity.User;
import static ua.training.util.constants.LoggerMessages.*;
public class LoggerMessageUtil {

    public static String userLogin(String login, User.Role role) {
        StringBuilder builder = new StringBuilder();
        builder.append(LOGIN).append(USER).append(login)
                .append(ROLE).append(role).append(LOGGED_THE_SYSTEM);

        return builder.toString();
    }

    public static String userLogged(String login, User.Role role) {
        StringBuilder builder = new StringBuilder();
        builder.append(LOGIN).append(USER).append(login)
                .append(ROLE).append(role).append(ALREADY_LOGGED);

        return builder.toString();
    }

    public static String userLoginWrongInput(String login) {
        StringBuilder builder = new StringBuilder();
        builder.append(LOGIN).append(USER).append(login).append(WRONG_INPUT);

        return builder.toString();
    }

    public static String userRegistration(String login, User.Role role) {
        StringBuilder builder = new StringBuilder();
        builder.append(REGISTRATION).append(USER).append(login).append(ROLE).append(role)
                .append(REGISTERED_THE_SYSTEM);

        return builder.toString();
    }

    public static String userLogout(String login) {
        StringBuilder builder = new StringBuilder();
        builder.append(LOGOUT).append(USER).append(login).append(LOGOUT_THE_SYSTEM);

        return builder.toString();
    }

    public static String daoException() {
        return DAO;
    }

    public static String unauthorizedAccess(String login, User.Role role) {
        StringBuilder builder = new StringBuilder();
        builder.append(AUTH_FILTER).append(USER).append(login).append(ROLE).append(role)
                .append(UNAUTHORIZED_ACCESS);

        return builder.toString();
    }

}
