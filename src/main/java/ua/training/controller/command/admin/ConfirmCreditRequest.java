package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.entity.Account;
import ua.training.model.entity.CreditRequest;
import ua.training.model.service.AccountService;
import ua.training.model.service.CreditRequestService;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ConfirmCreditRequest implements Command {
    private CreditRequestService creditRequestService;
    private AccountService accountService;

    public ConfirmCreditRequest(CreditRequestService creditRequestService, AccountService accountService) {
        this.creditRequestService = creditRequestService;
        this.accountService = accountService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int creditRequestId = Integer.parseInt(request.getParameter(AttributeNames.CREDIT_REQUEST_ID));
        Optional<CreditRequest> creditRequest = creditRequestService.findById(creditRequestId);

        accountService.create(Account.Type.CREDIT, creditRequest.get().getUserId(),
                creditRequest.get().getMoneyAmount());

        creditRequestService.delete(creditRequestId);
        return PageURLs.SHOW_CREDIT_REQUESTS;
    }
}
