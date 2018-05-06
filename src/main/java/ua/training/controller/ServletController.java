package ua.training.controller;

import ua.training.controller.command.CommandExecutor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ServletController extends HttpServlet {

    private CommandExecutor commandExecutor = new CommandExecutor();
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
        String page = commandExecutor.executeCommand(path, request);
        System.err.println(page);

        request.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    public void destroy(){
    }
}
