package ua.training.controller.command.user;

import ua.training.controller.command.Command;
import ua.training.model.service.AccountService;
import ua.training.util.ConvertUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;

public class ShowAccounts implements Command {
    private AccountService accountService;

    public ShowAccounts(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int loggedUserId = (int)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ID);

        request.getSession().setAttribute(AttributeNames.ACCOUNTS,
                ConvertUtil.convertMoneyToDollars(accountService.findAccountsByUserId(loggedUserId)));
        return PageURLs.ACCOUNT_INFO;
    }
}
