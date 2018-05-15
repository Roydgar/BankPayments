package ua.training.controller.command.user;

import ua.training.controller.command.Command;
import ua.training.model.entity.CreditRequest;
import ua.training.util.SortUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class SortCreditRequests implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> sortBy = Optional.of(request.getParameter(AttributeNames.SORT_BY));

        if (!sortBy.isPresent()) {
            return PageURLs.SHOW_CREDIT_REQUESTS;
        }

        @SuppressWarnings("unchecked")
        List<CreditRequest> creditRequests = (List<CreditRequest>)request.getSession().
                getAttribute(AttributeNames.CREDIT_REQUESTS);
        List<CreditRequest> sortedCreditRequests;

        if (sortBy.get().equals(AttributeNames.SORT_BY_MONEY_AMOUNT)) {
            sortedCreditRequests = SortUtil.sortCreditRequestsByMoneyAmount(creditRequests);
        } else {
            sortedCreditRequests = SortUtil.sortCreditRequestsByDate(creditRequests);
        }

        request.getSession().setAttribute(AttributeNames.CREDIT_REQUESTS, sortedCreditRequests);
        return PageURLs.SHOW_CREDIT_REQUESTS;
    }
}
