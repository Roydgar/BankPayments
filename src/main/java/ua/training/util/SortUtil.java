package ua.training.util;

import ua.training.model.entity.Account;
import ua.training.model.entity.CreditRequest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtil {
    public static List<Account> sortAccountsByBalance(List<Account> accounts) {
        accounts.sort(Comparator.comparing(Account::getBalance));
        return accounts;
    }

    public static List<Account> sortAccountsByType(List<Account> accounts) {
        accounts.sort(Comparator.comparing(o -> o.getType().toString()));
        return accounts;
    }

    public static List<Account> sortAccountsByDate(List<Account> accounts) {
        accounts.sort((o1, o2) -> - o1.getCreationDate().compareTo(o2.getCreationDate()));
        return accounts;
    }

    public static List<CreditRequest> sortCreditRequestsByMoneyAmount(List<CreditRequest> creditRequests) {
        creditRequests.sort(Comparator.comparing(CreditRequest::getMoneyAmount));
        return creditRequests;
    }

    public static List<CreditRequest> sortCreditRequestsByDate(List<CreditRequest> creditRequests) {
        creditRequests.sort((o1, o2) -> - o1.getDate().compareTo(o2.getDate()));
        return creditRequests;
    }
}
