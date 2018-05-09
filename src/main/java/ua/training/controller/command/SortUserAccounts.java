package ua.training.controller.command;

import ua.training.model.entity.Account;
import ua.training.util.SortUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SortUserAccounts implements Command{

    @Override
    public String execute(HttpServletRequest request) {
        String sortBy = request.getParameter(AttributeNames.SORT_BY);

        @SuppressWarnings("unchecked")
        List<Account> userAccounts = (List<Account>)request.getSession().getAttribute(AttributeNames.ACCOUNTS);
        List<Account> sortedAccounts;

        if (sortBy.equals(AttributeNames.SORT_BY_BALANCE)) {
            sortedAccounts = SortUtil.sortAccountsByBalance(userAccounts);
        } else if (sortBy.equals(AttributeNames.SORT_BY_TYPE)) {
            sortedAccounts = SortUtil.sortAccountsByType(userAccounts);
        } else {
            sortedAccounts = SortUtil.sortAccountsByDate(userAccounts);
        }

        request.getSession().setAttribute(AttributeNames.ACCOUNTS, sortedAccounts);
        return PageURLs.ACCOUNT_INDO;
    }
}
