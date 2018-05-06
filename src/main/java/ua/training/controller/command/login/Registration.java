package ua.training.controller.command.login;
import org.apache.commons.codec.digest.DigestUtils;
import ua.training.controller.command.Command;
import ua.training.model.entity.User;
import ua.training.model.service.UserService;
import ua.training.util.DataValidator;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;
import javax.servlet.http.HttpServletRequest;

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

        System.out.println(login + ": " + password);

        if (DataValidator.parameterIsEmptyOrNull(login, password, confirmPassword)) {
            System.out.println("SONE NULL");
            return PageURLs.REGISTRATION;
        }

        if(!DataValidator.passwordsAreEquals(password, confirmPassword)) {
            System.out.println("Passwords are not equals");
            return PageURLs.REGISTRATION;
        }

        System.out.println("NANA");

        if (userService.userExists(login)) {
            System.out.println("User exists");
            return PageURLs.REGISTRATION;
        }

        userService.create(login, password, User.Role.USER, email);

        return PageURLs.LOGIN;
    }
}