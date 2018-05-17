package ua.training.controller.command.user;

import ua.training.controller.annotation.CommandWithName;
import ua.training.controller.command.Command;
import ua.training.model.entity.Account;
import ua.training.model.service.AccountService;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.CommandNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CommandWithName(name = CommandNames.SHOW_ACCOUNTS)
public class ShowAccounts implements Command {
    private AccountService accountService;

    public ShowAccounts(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int loggedUserId = (int)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ID);
        List<Account> accounts = accountService.findAccountsByUserId(loggedUserId);


        request.getSession().setAttribute(AttributeNames.ACCOUNTS, accounts);
        return PageURLs.ACCOUNT_INFO;
    }
}
