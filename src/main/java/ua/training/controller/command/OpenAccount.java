package ua.training.controller.command;

import org.javamoney.moneta.Money;
import ua.training.model.entity.Account;
import ua.training.model.entity.User;
import ua.training.model.service.AccountService;
import ua.training.model.service.CreditRequestService;
import ua.training.util.AccountUtil;
import ua.training.util.UserUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class OpenAccount implements Command {
    private AccountService accountService;
    private CreditRequestService creditRequestService;

    OpenAccount(AccountService accountService, CreditRequestService creditRequestService) {
        this.accountService = accountService;
        this.creditRequestService = creditRequestService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        Account.Type type = Account.Type.valueOf(request.getParameter(AttributeNames.ACCOUNT_TYPE).toUpperCase());
        int loggedUserId = (int)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ID);

        if (type == Account.Type.CHECKING) {
            accountService.create(type, loggedUserId);
        } else if(type == Account.Type.DEPOSIT) {
            Money moneyAmount = AccountUtil.getMoneyInDefaultCurrency(
                    Long.parseLong(request.getParameter(AttributeNames.MONEY_AMOUNT)));

            accountService.create(type, loggedUserId, moneyAmount);
        }  else if(type == Account.Type.CREDIT) {
            Money moneyAmount = AccountUtil.getMoneyInDefaultCurrency(
                    Long.parseLong(request.getParameter(AttributeNames.MONEY_AMOUNT)));

            creditRequestService.create(loggedUserId, moneyAmount);
        }

        request.getSession().setAttribute(AttributeNames.ACCOUNTS,
                accountService.findAccountsByUserId(loggedUserId));

        User.Role role = (User.Role)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ROLE);
        return UserUtil.getPageByRole(role);
    }
}
