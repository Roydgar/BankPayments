package ua.training.controller.command.user;

import ua.training.controller.annotation.CommandWithName;
import ua.training.controller.command.Command;
import ua.training.model.entity.Account;
import ua.training.model.entity.User;
import ua.training.model.service.AccountService;
import ua.training.model.service.UserService;
import ua.training.util.DataValidator;
import ua.training.util.ResourceBundleUtil;
import ua.training.util.UserUtil;
import ua.training.util.constants.AttributeNames;

import ua.training.util.constants.CommandNames;
import ua.training.util.constants.PageURLs;
import ua.training.util.constants.ResponseMessages;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@CommandWithName(name = CommandNames.ADD_USER_TO_ACCOUNT)
public class AddUserToAccount implements Command {

    private AccountService accountService;
    private UserService userService;

    public AddUserToAccount(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String chosenAccountNumber = request.getParameter(AttributeNames.CHOSEN_ACCOUNT);

        if (DataValidator.parameterIsEmptyOrNull(chosenAccountNumber)) {
            return PageURLs.ADD_USER_TO_ACCOUNT;
        }

        Optional<Account> chosenAccount = accountService.findByNumber(
                request.getParameter(AttributeNames.CHOSEN_ACCOUNT));
        Optional<User> chosenUser = userService.getUserByLogin(request.getParameter(AttributeNames.LOGIN));

        if (chosenUser.isPresent() && chosenAccount.isPresent()) {
            accountService.addUserToAccount(chosenUser.get().getId(), chosenAccount.get().getId());
        } else {
            ResourceBundleUtil.setErrorMessage(request, ResponseMessages.LOGIN_DOESNT_EXIST);
            return PageURLs.ADD_USER_TO_ACCOUNT;
        }

        User.Role role = (User.Role)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ROLE);
        return UserUtil.getPageByRole(role);
    }

}
