package ua.training.controller.command;

import ua.training.model.entity.Account;
import ua.training.model.entity.User;
import ua.training.model.service.AccountService;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;

public class SelectAccount implements Command{
    private AccountService accountService;

    public SelectAccount(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        int currentUserId = (int)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ID);

        for (Account account : accountService.findAccountsByUserId(currentUserId)) {
            if (request.getParameter(account.getNumber()) != null) {
                request.getSession().setAttribute(AttributeNames.SELECTED_ACCOUNT, account);
            }
        }
        return getPageByRole((User.Role)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ROLE));
    }

    private String getPageByRole(User.Role userRole) {
        return userRole == User.Role.ADMIN ? PageURLs.REDIRECT_ADMIN_MENU : PageURLs.REDIRECT_USER_MENU;
    }
}
