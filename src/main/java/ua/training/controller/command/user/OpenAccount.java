package ua.training.controller.command.user;

import org.javamoney.moneta.Money;
import ua.training.controller.command.Command;
import ua.training.model.entity.Account;
import ua.training.model.entity.User;
import ua.training.model.service.AccountService;
import ua.training.model.service.CreditRequestService;
import ua.training.util.*;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;
import ua.training.util.constants.ResponseMessages;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class OpenAccount implements Command {
    private AccountService accountService;
    private CreditRequestService creditRequestService;

    public OpenAccount(AccountService accountService, CreditRequestService creditRequestService) {
        this.accountService = accountService;
        this.creditRequestService = creditRequestService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        String accountType = request.getParameter(AttributeNames.ACCOUNT_TYPE);
        String moneyAmountParameter = request.getParameter(AttributeNames.MONEY_AMOUNT);

        if (DataValidator.parameterIsEmptyOrNull(accountType, moneyAmountParameter)) {
            return PageURLs.OPEN_ACCOUNT;
        }

        Account.Type type = Account.Type.valueOf(accountType.toUpperCase());
        int loggedUserId = (int)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ID);
        Money moneyAmount = AccountUtil.getMoneyInDefaultCurrency(moneyAmountParameter);

        if (AccountUtil.moneyIsBiggerThanLimit(moneyAmount, type)) {
            ResourceBundleUtil.setErrorMessage(request, ResponseMessages.OPEN_ACCOUNT_DENIED);
            return PageURLs.OPEN_ACCOUNT;
        }

        if (type == Account.Type.CREDIT) {
            creditRequestService.create(loggedUserId,
                    (String)request.getSession().getAttribute(AttributeNames.LOGGED_USER_LOGIN),
                    ConvertUtil.convertDollarsToCents(moneyAmount));
        } else{
            accountService.create(type, loggedUserId, ConvertUtil.convertDollarsToCents(moneyAmount));
        }

        request.getSession().setAttribute(AttributeNames.ACCOUNTS,
                ConvertUtil.convertMoneyToDollars(accountService.findAccountsByUserId(loggedUserId)));

        User.Role role = (User.Role)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ROLE);
        return UserUtil.getPageByRole(role);
    }
}
