package ua.training.controller.command;

import ua.training.exception.NoResultFromDbException;
import ua.training.model.entity.Account;
import ua.training.model.entity.User;
import ua.training.model.service.AccountService;
import ua.training.model.service.UserService;
import ua.training.util.ResourceBundleUtil;
import ua.training.util.UserUtil;
import ua.training.util.constants.AttributeNames;

import ua.training.util.constants.ResponseMessages;

import javax.servlet.http.HttpServletRequest;

public class AddUserToAccount implements Command{

    private AccountService accountService;
    private UserService userService;

    AddUserToAccount(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public String execute(HttpServletRequest request) {


        try {
            Account chosenAccount = accountService.findByNumber(
                    request.getParameter(AttributeNames.CHOSEN_ACCOUNT));
            User chosenUser = userService.getUserByLogin(request.getParameter(AttributeNames.LOGIN));

            accountService.addUserToAccount(chosenUser.getId(), chosenAccount.getId());
        } catch (NoResultFromDbException e){
            ResourceBundleUtil.setErrorMessage(request, ResponseMessages.LOGIN_DOESNT_EXIST);
        }

        User.Role role = (User.Role)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ROLE);
        return UserUtil.getPageByRole(role);
    }

}
