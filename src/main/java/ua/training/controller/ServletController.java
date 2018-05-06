package ua.training.controller;

import ua.training.controller.command.Command;
import ua.training.controller.command.CommandCreator;
import ua.training.controller.command.login.Login;
import ua.training.controller.command.login.Logout;
import ua.training.controller.command.login.Registration;
import ua.training.model.service.UserService;
import ua.training.util.constants.AttributeNames;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServletController extends HttpServlet {
    private CommandCreator commandCreator = new CommandCreator();
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }


    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        path = path.replaceAll(".*/" , "");

        System.out.println(path);
        String page = commandCreator.executeCommand(path, request);
        System.err.println(page);

        request.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    public void destroy(){
    }
}
