package ua.training.controller.command.user;

import org.javamoney.moneta.Money;
import ua.training.controller.command.Command;
import ua.training.model.entity.Account;
import ua.training.model.entity.Operation;
import ua.training.model.entity.User;
import ua.training.model.service.AccountService;
import ua.training.model.service.OperationService;
import ua.training.util.*;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;
import ua.training.util.constants.ResponseMessages;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class DoOperation implements Command {
    private AccountService accountService;
    private OperationService operationService;

    public DoOperation(OperationService operationService, AccountService accountService) {
        this.operationService = operationService;
        this.accountService = accountService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String moneyAmountParameter =  request.getParameter(AttributeNames.MONEY_AMOUNT);
        String payerAccountNumber   =  request.getParameter(AttributeNames.PAYER_ACCOUNT);
        String recipientAccountNumber = request.getParameter(AttributeNames.RECIPIENT_ACCOUNT);

        if (DataValidator.parameterIsEmptyOrNull(moneyAmountParameter, payerAccountNumber, recipientAccountNumber)) {
            return PageURLs.DO_OPERATION;
        }

        Money moneyAmount = ConvertUtil.convertDollarsToCents(AccountUtil.getMoneyInDefaultCurrency(
                moneyAmountParameter));

        Optional<Account> payerAccount = accountService.findByNumber(payerAccountNumber);
        Optional<Account> recipientAccount = accountService.findByNumber(recipientAccountNumber);

        if (!payerAccount.isPresent() || !recipientAccount.isPresent()) {
            ResourceBundleUtil.setErrorMessage(request, ResponseMessages.RECIPIENT_DOESNT_EXIST);
            return PageURLs.DO_OPERATION;
        }

        if (payerAccount.get().getBalance().isLessThan(moneyAmount)) {
            ResourceBundleUtil.setErrorMessage(request, ResponseMessages.INSUFFICIENT_FUNDS);
            return PageURLs.DO_OPERATION;
        }

        accountService.updateBalance(recipientAccount.get().getId(),
                recipientAccount.get().getBalance().add(moneyAmount));
        accountService.updateBalance(payerAccount.get().getId(),
                payerAccount.get().getBalance().subtract(moneyAmount));
        operationService.create(payerAccount.get().getId(), recipientAccount.get().getNumber(),
                recipientAccount.get().getType() == Account.Type.CREDIT ?
                        Operation.Type.LOAN_PAYMENT : Operation.Type.TRANSFER, moneyAmount);

        int loggedUserId = (int)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ID);
        request.getSession().setAttribute(AttributeNames.ACCOUNTS,
                ConvertUtil.convertMoneyToDollars(accountService.findAccountsByUserId(loggedUserId)));

        User.Role role = (User.Role)request.getSession().getAttribute(AttributeNames.LOGGED_USER_ROLE);
        return UserUtil.getPageByRole(role);
    }

}
