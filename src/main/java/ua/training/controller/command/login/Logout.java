package ua.training.controller.command.login;


import ua.training.controller.command.Command;
import ua.training.model.entity.User;
import ua.training.util.LoggerMessageUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

public class Logout implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String login = (String)request.getSession().getAttribute(AttributeNames.LOGGED_USER_LOGIN);
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute(AttributeNames.LOGGED_USERS);

        loggedUsers.remove(login);

        request.getSession().setAttribute(AttributeNames.LOGGED_USER_ROLE, User.Role.UNKNOWN);
        request.getSession().getServletContext().setAttribute(AttributeNames.LOGGED_USERS, loggedUsers);

        logger.info(LoggerMessageUtil.userLogout(login));
        return PageURLs.INDEX;
    }
}
