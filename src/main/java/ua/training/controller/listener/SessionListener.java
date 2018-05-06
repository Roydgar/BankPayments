package ua.training.controller.listener;


import ua.training.model.entity.User;
import ua.training.util.constants.AttributeNames;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute(AttributeNames.LOGGED_USERS);

        String loggedUserLogin = (String)httpSessionEvent.getSession()
                .getAttribute(AttributeNames.LOGGED_USER_LOGIN);

        loggedUsers.remove(loggedUserLogin);

        httpSessionEvent.getSession().setAttribute(AttributeNames.LOGGED_USERS, loggedUsers);
    }
}
