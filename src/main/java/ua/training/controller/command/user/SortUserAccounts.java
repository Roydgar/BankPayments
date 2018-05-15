package ua.training.controller.command.user;

import ua.training.controller.command.Command;
import ua.training.model.entity.Account;
import ua.training.util.SortUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class SortUserAccounts implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        Optional<String> sortBy = Optional.of(request.getParameter(AttributeNames.SORT_BY));

        if (!sortBy.isPresent()) {
            return PageURLs.ACCOUNT_INFO;
        }

        @SuppressWarnings("unchecked")
        List<Account> userAccounts = (List<Account>)request.getSession().getAttribute(AttributeNames.ACCOUNTS);
        List<Account> sortedAccounts;

        if (sortBy.get().equals(AttributeNames.SORT_BY_BALANCE)) {
            sortedAccounts = SortUtil.sortAccountsByBalance(userAccounts);
        } else if (sortBy.get().equals(AttributeNames.SORT_BY_TYPE)) {
            sortedAccounts = SortUtil.sortAccountsByType(userAccounts);
        } else {
            sortedAccounts = SortUtil.sortAccountsByDate(userAccounts);
        }

        request.getSession().setAttribute(AttributeNames.ACCOUNTS, sortedAccounts);
        return PageURLs.ACCOUNT_INFO;
    }
}
