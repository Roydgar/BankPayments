package ua.training.controller;

import ua.training.util.constants.AttributeNames;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashSet;

@WebListener
public class ContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute(AttributeNames.LOGGED_USERS, new HashSet<String>());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
