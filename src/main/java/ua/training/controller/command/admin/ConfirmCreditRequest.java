package ua.training.controller.command.admin;

import ua.training.controller.annotation.CommandWithName;
import ua.training.controller.command.Command;
import ua.training.model.entity.Account;
import ua.training.model.entity.CreditRequest;
import ua.training.model.service.AccountService;
import ua.training.model.service.CreditRequestService;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.CommandNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@CommandWithName(name = CommandNames.CONFIRM_CREDIT_REQUEST)
public class ConfirmCreditRequest implements Command {
    private CreditRequestService creditRequestService;
    private AccountService accountService;

    public ConfirmCreditRequest(CreditRequestService creditRequestService,
                                AccountService accountService) {
        this.creditRequestService = creditRequestService;
        this.accountService = accountService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int creditRequestId = Integer.parseInt(
                request.getParameter(AttributeNames.CREDIT_REQUEST_ID).split(":")[0]);
        CreditRequest.Status status = CreditRequest.Status.valueOf(
                request.getParameter(AttributeNames.CREDIT_REQUEST_ID).split(":")[1].toUpperCase());

        Optional<CreditRequest> creditRequest = creditRequestService.findById(creditRequestId);

        if (status == CreditRequest.Status.CONFIRMED) {
            accountService.create(Account.Type.CREDIT, creditRequest.get().getUserId(),
                    creditRequest.get().getMoneyAmount().negate());
        }

        creditRequestService.updateStatus(creditRequestId, status);

        request.getSession().setAttribute(AttributeNames.CREDIT_REQUESTS, creditRequestService.findAll());
        return PageURLs.SHOW_CREDIT_REQUESTS_REDIRECT;
    }
}
