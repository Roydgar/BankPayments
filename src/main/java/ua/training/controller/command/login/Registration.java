package ua.training.controller.command.login;
import ua.training.controller.command.Command;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.util.DataValidator;
import ua.training.util.ResourceBundleUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.ResponseMessages;
import ua.training.util.constants.PageURLs;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class Registration implements Command {

    private UserService userService;

    public Registration(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(AttributeNames.LOGIN);
        String email = request.getParameter(AttributeNames.EMAIL);
        String password = request.getParameter(AttributeNames.PASSWORD);
        String confirmPassword = request.getParameter(AttributeNames.CONFIRM_PASSWORD);


        if (DataValidator.parameterIsEmptyOrNull(login, password, confirmPassword)) {
            return PageURLs.REGISTRATION;
        }

        if(!DataValidator.passwordsAreEquals(password, confirmPassword)) {
            setErrorMessage(request, ResponseMessages.REGISTRATION_PASSWORDS_DONT_MATCH);
            return PageURLs.REGISTRATION;
        }

        if (userService.userExists(login)) {
            setErrorMessage(request, ResponseMessages.REGISTRATION_USER_EXISTS);
            return PageURLs.REGISTRATION;
        }

        userService.create(login, password, User.Role.USER, email);

        return PageURLs.LOGIN;
    }

    private void setErrorMessage(HttpServletRequest request, String errorMessage) {
        request.setAttribute(AttributeNames.WRONG_INPUT_MESSAGE, ResourceBundleUtil.getPropertyFromLangBundle(
                errorMessage, (Locale)request.getSession().getAttribute(AttributeNames.LANGUAGE)));
    }
}