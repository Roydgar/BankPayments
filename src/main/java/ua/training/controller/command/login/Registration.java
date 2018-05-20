package ua.training.controller.command.login;
import ua.training.controller.annotation.CommandWithName;
import ua.training.controller.command.Command;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.util.DataValidator;
import ua.training.util.LoggerMessageUtil;
import ua.training.util.ResourceBundleUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.CommandNames;
import ua.training.util.constants.ResponseMessages;
import ua.training.util.constants.PageURLs;
import javax.servlet.http.HttpServletRequest;

@CommandWithName(name = CommandNames.REGISTRATION)
public class Registration implements Command {
    private final UserService userService;

    public Registration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(AttributeNames.LOGIN);
        String email = request.getParameter(AttributeNames.EMAIL);
        String password = request.getParameter(AttributeNames.PASSWORD);
        String confirmPassword = request.getParameter(AttributeNames.CONFIRM_PASSWORD);

        if (DataValidator.parameterIsEmptyOrNull(login, email, password, confirmPassword)) {
            return PageURLs.REGISTRATION;
        }

        if(!password.equals(confirmPassword)) {
            ResourceBundleUtil.setErrorMessage(request, ResponseMessages.REGISTRATION_PASSWORDS_DONT_MATCH);
            return PageURLs.REGISTRATION;
        }

        if (userService.userExists(login)) {
            ResourceBundleUtil.setErrorMessage(request, ResponseMessages.REGISTRATION_USER_EXISTS);
            return PageURLs.REGISTRATION;
        }

        userService.create(login, password, User.Role.USER, email);
        logger.info(LoggerMessageUtil.userRegistration(login, User.Role.USER));
        return PageURLs.LOGIN;
    }
}