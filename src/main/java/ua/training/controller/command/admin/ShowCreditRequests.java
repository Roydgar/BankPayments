package ua.training.controller.command.admin;

import ua.training.controller.command.Command;
import ua.training.model.service.CreditRequestService;
import ua.training.util.ConvertUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;

public class ShowCreditRequests implements Command {
    private CreditRequestService creditRequestService;

    public ShowCreditRequests(CreditRequestService creditRequestService) {
        this.creditRequestService = creditRequestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().setAttribute(AttributeNames.CREDIT_REQUESTS,
                ConvertUtil.convertCreditMoneyToDollars(creditRequestService.findAll()));
        return PageURLs.SHOW_CREDIT_REQUESTS;
    }
}
