package ua.training.controller.command;

import ua.training.model.entity.Account;
import ua.training.model.service.AccountService;
import ua.training.util.DataValidator;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAccounts implements Command{
    private AccountService accountService;

    public ShowAccounts(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        List<Account> accounts = accountService.findAccountsByUserId(
                (Integer)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ID));

        request.getSession().setAttribute(AttributeNames.ACCOUNTS, accounts);
        return PageURLs.USER_MENU;
    }
}
