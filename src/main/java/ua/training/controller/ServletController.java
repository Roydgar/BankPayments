package ua.training.controller;

import ua.training.controller.command.CommandExecutor;
import ua.training.util.constants.AttributeNames;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class ServletController extends HttpServlet {

    private CommandExecutor commandExecutor = CommandExecutor.getInstance();
    @Override
    public void init(ServletConfig servletConfig){
        servletConfig.getServletContext().setAttribute(AttributeNames.LOGGED_USERS, new HashSet<String>());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(request.getSession().getAttribute(AttributeNames.LANGUAGE));
        if (request.getSession().getAttribute(AttributeNames.LANGUAGE) != null) {
            String locale = request.getSession().getAttribute(AttributeNames.LANGUAGE).toString();
            System.out.println(locale);
        }
        String path = request.getRequestURI();

        path = path.replaceAll(".*/" , "");

        String page = commandExecutor.executeCommand(path, request);
        if(page.contains("redirect")){
            response.sendRedirect(page.replace("redirect:", ""));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    @Override
    public void destroy(){
    }
}
