package ua.training.controller.command;

import ua.training.model.entity.Account;
import ua.training.model.service.AccountService;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;

public class OpenAccount implements Command {
    private AccountService accountService;

    OpenAccount(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        Account.Type type = Account.Type.valueOf(request.getParameter(AttributeNames.ACCOUNT_TYPE).toUpperCase());
        int loggedUserId = (int)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ID);

        accountService.create(type, loggedUserId);

        return PageURLs.USER_MENU;
    }
}
