package ua.training.util;

import org.javamoney.moneta.Money;
import ua.training.model.entity.Account;
import ua.training.model.entity.CreditRequest;
import ua.training.model.entity.Operation;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

public class ConvertUtil {
    public static List<Account> convertMoneyToDollars(List<Account> accounts) {
        for (Account account : accounts) {
            account.setBalance(account.getBalance().divide(100));
        }
        return accounts;
    }

    public static List<CreditRequest> convertCreditMoneyToDollars(List<CreditRequest> requests) {
        for (CreditRequest request : requests) {
            request.setMoneyAmount(request.getMoneyAmount().divide(100));
        }
        return requests;
    }

    public static List<Operation> convertOperationMoneyToDollars(List<Operation> operations) {
        for (Operation operation : operations) {
            operation .setMoneyAmount(operation .getMoneyAmount().divide(100));
        }
        return operations;
    }

    public static  Money convertDollarsToCents(Money dollars) {
        return dollars.multiply(100);
    }
}
