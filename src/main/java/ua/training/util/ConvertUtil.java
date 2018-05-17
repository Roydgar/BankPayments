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
    public static  Money convertDollarsToCents(Money dollars) {
        return dollars.multiply(100);
    }
}
