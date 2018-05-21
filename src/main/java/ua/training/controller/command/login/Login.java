package ua.training.controller.command.login;

import ua.training.controller.annotation.CommandWithName;
import ua.training.controller.command.Command;
import ua.training.model.entity.User;
import ua.training.model.service.AccountService;
import ua.training.model.service.UserService;
import ua.training.util.*;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.CommandNames;
import ua.training.util.constants.ResponseMessages;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@CommandWithName(name = CommandNames.LOGIN)
public class Login implements Command {

    private final UserService userService;
    private final AccountService accountService;

    public Login(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(AttributeNames.LOGIN);
        String password = request.getParameter(AttributeNames.PASSWORD);

        if (DataValidator.parameterIsEmptyOrNull(login, password)) {
            return PageURLs.LOGIN;
        }

        if(!DataValidator.checkUserInput(login, password)) {
            ResourceBundleUtil.setErrorMessage(request, ResponseMessages.LOGIN_ERROR);
            return PageURLs.LOGIN;
        }

        Optional<User> user = userService.login(login, password);

        if (!user.isPresent()) {
            ResourceBundleUtil.setErrorMessage(request, ResponseMessages.LOGIN_ERROR);
            logger.info(LoggerMessageUtil.userLoginWrongInput(login));
            return PageURLs.LOGIN;
        }

        User loggedUser = user.get();
        User.Role role = loggedUser.getRole();

        logger.info(LoggerMessageUtil.userLogin(login, role));

        if(userService.userIsLogged(request, login)) {
            ResourceBundleUtil.setErrorMessage(request, ResponseMessages.LOGIN_USER_IS_LOGGED);
            logger.warn(LoggerMessageUtil.userLogged(login, role));
            return PageURLs.ERROR;
        }

        request.getSession().setAttribute(AttributeNames.LOGGED_USER_ID, loggedUser.getId());
        request.getSession().setAttribute(AttributeNames.LOGGED_USER_LOGIN, loggedUser.getLogin().toLowerCase());
        request.getSession().setAttribute(AttributeNames.LOGGED_USER_ROLE, role);
        request.getSession().setAttribute(AttributeNames.ACCOUNTS, accountService.findAccountsByUserId(loggedUser.getId()));

        return UserUtil.getPageByRole(role);
    }

}
