package ua.training.controller;

import org.apache.log4j.PropertyConfigurator;
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

        String log4jConfigFile = servletConfig.getServletContext().getInitParameter("log4j-config-location");
        String fullPath = servletConfig.getServletContext().getRealPath("") + File.separator + log4jConfigFile;
        PropertyConfigurator.configure(fullPath);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("DOOOGEEET");

        processRequest(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("DOOOPOOST");
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
