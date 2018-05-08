package ua.training.controller.command;

import ua.training.model.entity.Account;
import ua.training.model.entity.User;
import ua.training.model.service.AccountService;
import ua.training.util.UserUtil;
import ua.training.util.constants.AttributeNames;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SelectAccount implements Command{
    private AccountService accountService;

    SelectAccount(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        int currentUserId = (int)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ID);

        //todo: get from list
        Optional<Account> selectedAccount = accountService.findByNumber(request.getParameter("accountToSelect"));
        System.out.println(selectedAccount.get());

        request.getSession().setAttribute(AttributeNames.SELECTED_ACCOUNT, selectedAccount.get());


        User.Role role = (User.Role)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ROLE);
        return UserUtil.getPageByRole(role);
    }

}
