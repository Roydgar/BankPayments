package ua.training.controller.command.user;

import org.javamoney.moneta.Money;
import ua.training.controller.annotation.CommandWithName;
import ua.training.controller.command.Command;
import ua.training.model.entity.Account;
import ua.training.model.entity.User;
import ua.training.model.service.AccountService;
import ua.training.model.service.CreditRequestService;
import ua.training.util.*;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.CommandNames;
import ua.training.util.constants.PageURLs;
import ua.training.util.constants.ResponseMessages;

import javax.servlet.http.HttpServletRequest;

@CommandWithName(name = CommandNames.OPEN_ACCOUNT)
public class OpenAccount implements Command {
    private final AccountService accountService;
    private final CreditRequestService creditRequestService;

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
        Money moneyAmount = ConvertUtil.convertDollarsToCents(
                AccountUtil.getMoneyInDefaultCurrency(moneyAmountParameter));

        if (AccountUtil.moneyIsBiggerThanLimit(moneyAmount, type)) {
            ResourceBundleUtil.setErrorMessage(request, ResponseMessages.OPEN_ACCOUNT_DENIED);
            return PageURLs.OPEN_ACCOUNT;
        }

        if (type == Account.Type.CREDIT) {
            creditRequestService.create(loggedUserId,
                    (String)request.getSession().getAttribute(AttributeNames.LOGGED_USER_LOGIN), moneyAmount);
        } else{
            System.out.println("CRESDF");
            accountService.create(type, loggedUserId, moneyAmount);
        }

        request.getSession().setAttribute(AttributeNames.ACCOUNTS, accountService.findAccountsByUserId(loggedUserId));

        User.Role role = (User.Role)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ROLE);
        return UserUtil.getPageByRole(role);
    }
}
