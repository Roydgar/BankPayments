package ua.training.controller.command.login;

import ua.training.controller.command.Command;
import ua.training.exception.NoResultFromDbException;
import ua.training.model.entity.User;
import ua.training.model.service.AccountService;
import ua.training.model.service.UserService;
import ua.training.util.ResourceBundleUtil;
import ua.training.util.UserUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.ResponseMessages;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;


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

        User user;
        try {
            user = userService.login(login, password);
        } catch (NoResultFromDbException e) {
            ResourceBundleUtil.setErrorMessage(request, ResponseMessages.LOGIN_ERROR);
            return PageURLs.LOGIN;
        }

        if(userService.userIsLogged(request, login)) {
            ResourceBundleUtil.setErrorMessage(request, ResponseMessages.LOGIN_USER_IS_LOGGED);
            return PageURLs.ERROR;
        }

        request.getSession().setAttribute(AttributeNames.LOGGED_USER_ID, user.getId());
        request.getSession().setAttribute(AttributeNames.LOGGED_USER_LOGIN, user.getLogin().toLowerCase());
        request.getSession().setAttribute(AttributeNames.LOGGED_USER_ROLE, user.getRole());
        request.getSession().setAttribute(AttributeNames.ACCOUNTS,
                accountService.findAccountsByUserId(user.getId()));

        return UserUtil.getPageByRole(user.getRole());
    }

}
