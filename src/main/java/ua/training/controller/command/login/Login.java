package ua.training.controller.command.login;

import ua.training.controller.command.Command;
import ua.training.exception.LoginFailedException;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.util.DataValidator;
import ua.training.util.ResourceBundleUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.ResponseMessages;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class Login implements Command {

    private UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(AttributeNames.LOGIN);
        String password = request.getParameter(AttributeNames.PASSWORD);

        if (DataValidator.parameterIsEmptyOrNull(login, password)) {
            return PageURLs.LOGIN;
        }

        User user;
        try {
            user = userService.login(login, password);
        } catch (LoginFailedException e) {
            setErrorMessage(request, ResponseMessages.LOGIN_ERROR);
            return PageURLs.LOGIN;
        }

        if(userService.userIsLogged(request, login)) {
            setErrorMessage(request, ResponseMessages.LOGIN_USER_IS_LOGGED);
            return PageURLs.ERROR;
        }

        request.getSession().setAttribute(AttributeNames.LOGGED_USER_LOGIN, user.getLogin().toLowerCase());
        request.getSession().setAttribute(AttributeNames.LOGGED_USER_ROLE, user.getRole());

        return getPageByRole(user);
    }

    private String getPageByRole(User user) {
        return user.getRole() == User.Role.ADMIN ? PageURLs.ADMIN_MENU : PageURLs.USER_MENU;
    }

    private void setErrorMessage(HttpServletRequest request, String message) {
        Locale locale = (Locale)request.getSession().getAttribute(AttributeNames.LANGUAGE);
        request.setAttribute(AttributeNames.WRONG_INPUT_MESSAGE, ResourceBundleUtil.getMessage(message, locale));
    }
}
