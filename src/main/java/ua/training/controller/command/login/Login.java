package ua.training.controller.command.login;

import ua.training.controller.command.Command;
import ua.training.model.entity.User;
import ua.training.model.service.AccountService;
import ua.training.model.service.UserService;
import ua.training.util.ConvertUtil;
import ua.training.util.ResourceBundleUtil;
import ua.training.util.UserUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.ResponseMessages;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class Login implements Command {

    private UserService userService;
    private AccountService accountService;

    public Login(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(AttributeNames.LOGIN);
        String password = request.getParameter(AttributeNames.PASSWORD);

        Optional<User> user = userService.login(login, password);

        if (!user.isPresent()) {
            ResourceBundleUtil.setErrorMessage(request, ResponseMessages.LOGIN_ERROR);
            return PageURLs.LOGIN;
        }

        if(userService.userIsLogged(request, login)) {
            ResourceBundleUtil.setErrorMessage(request, ResponseMessages.LOGIN_USER_IS_LOGGED);
            return PageURLs.ERROR;
        }

        User loggedUser = user.get();

        request.getSession().setAttribute(AttributeNames.LOGGED_USER_ID, loggedUser.getId());
        request.getSession().setAttribute(AttributeNames.LOGGED_USER_LOGIN, loggedUser.getLogin().toLowerCase());
        request.getSession().setAttribute(AttributeNames.LOGGED_USER_ROLE, loggedUser.getRole());

        return UserUtil.getPageByRole(loggedUser.getRole());
    }

}
