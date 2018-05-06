package ua.training.controller;

import ua.training.controller.command.Command;
import ua.training.controller.command.login.Login;
import ua.training.controller.command.login.Logout;
import ua.training.controller.command.login.Registration;
import ua.training.model.service.UserService;

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
    private Map<String, Command> commands = new ConcurrentHashMap<>();
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        UserService userService = new UserService();
        System.out.println("Init");

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("login", new Login(userService));
        commands.put("registration", new Registration(userService));
        commands.put("logout", new Logout());
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

        Command command = commands.getOrDefault(path,
                (r)->"index.jsp");
        System.out.println(path);
        String page = command.execute(request);
        System.err.println(page);
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
