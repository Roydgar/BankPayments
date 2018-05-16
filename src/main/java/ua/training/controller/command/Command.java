package ua.training.controller.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Logger logger = Logger.getRootLogger();

    String execute(HttpServletRequest request);
}
