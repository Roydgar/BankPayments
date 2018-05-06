package ua.training.controller.command.login;

import ua.training.controller.command.Command;
import ua.training.exception.UserDoesntExistException;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.util.DataValidator;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class Login implements Command {

    private UserService userService;
    public Login(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(AttributeNames.LOGIN);
        String password = request.getParameter(AttributeNames.PASSWORD);

        System.out.println(login + " : " + password);

        if (DataValidator.parameterIsEmptyOrNull(login, password)) {
            return PageURLs.LOGIN;
        }



        User user;
        try {
            user = userService.login(login, password);
        } catch (UserDoesntExistException e) {
            System.out.println("User doesnt exist");
            return PageURLs.LOGIN;
        }

        if(userService.userIsLogged(request, login)) {
            System.out.println("USER LOGGED");
            return PageURLs.ERROR;
        }

        System.out.println("Founded user: " + user);

        request.getSession().setAttribute(AttributeNames.LOGGED_USER_LOGIN, user.getLogin().toLowerCase());
        request.getSession().setAttribute(AttributeNames.LOGGED_USER_ROLE, user.getRole());

        return getPageByRole(user);
    }

    private String getPageByRole(User user) {
        return user.getRole() == User.Role.ADMIN ? PageURLs.ADMIN_MENU : PageURLs.USER_MENU;
    }
}