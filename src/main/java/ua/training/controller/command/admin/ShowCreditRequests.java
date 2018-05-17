package ua.training.controller.command.admin;

import ua.training.controller.annotation.CommandWithName;
import ua.training.controller.command.Command;
import ua.training.model.service.CreditRequestService;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.CommandNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;

@CommandWithName(name = CommandNames.SHOW_CREDIT_REQUESTS)
public class ShowCreditRequests implements Command {
    private CreditRequestService creditRequestService;

    public ShowCreditRequests(CreditRequestService creditRequestService) {
        this.creditRequestService = creditRequestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute(AttributeNames.CREDIT_REQUESTS, creditRequestService.findAll());

        return PageURLs.SHOW_CREDIT_REQUESTS_REDIRECT;
    }
}
