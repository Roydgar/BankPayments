package ua.training.view;

import org.apache.logging.log4j.LogManager;
import org.javamoney.moneta.Money;
import org.javamoney.moneta.format.CurrencyStyle;
import ua.training.util.LoggerMessageUtil;

import javax.money.Monetary;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.Locale;

import static java.util.Objects.isNull;

public class MoneyFormatTag extends SimpleTagSupport {
    private Money money;
    private String currencyCode;


    public void setMoney(Money money) {
        this.money = money.divide(100);
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public void doTag() {

        CurrencyConversion conversion = MonetaryConversions.getConversion(currencyCode);
        Money convertedMoney = money.with(conversion).with(Monetary.getDefaultRounding());
        try {
            getJspContext()
                    .getOut()
                    .write(String.valueOf(convertedMoney.getNumber().doubleValue()) + " " +
                            conversion.getCurrency());
        } catch (IOException e) {
            LogManager.getRootLogger().error(LoggerMessageUtil.JstlTagException(), e);
            throw new RuntimeException(e);
        }
    }
}