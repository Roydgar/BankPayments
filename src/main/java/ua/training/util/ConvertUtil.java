package ua.training.util;

import org.javamoney.moneta.Money;


public class ConvertUtil {
    public static  Money convertDollarsToCents(Money dollars) {
        return dollars.multiply(100);
    }
}
