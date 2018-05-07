package ua.training.controller.command;

import ua.training.model.dao.DaoFactory;
import ua.training.model.entity.Account;
import ua.training.model.service.AccountService;
import ua.training.util.DataValidator;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;

public class OpenAccount implements Command {
    private AccountService accountService;

    public OpenAccount(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        String type = request.getParameter(AttributeNames.ACCOUNT_TYPE);

        accountService.create(type,
                (Integer)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ID));

        return PageURLs.USER_MENU;
    }
}
