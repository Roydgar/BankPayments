package ua.training.util;

import org.javamoney.moneta.Money;
import org.junit.Test;
import ua.training.util.constants.GlobalConstants;

import static org.junit.Assert.*;

public class ConvertUtilTest {

    @Test
    public void convertDollarsToCentsTrueTest() {
        Money money = Money.of(1.0, GlobalConstants.DEFAULT_CURRENCY);
        Money expected = Money.of(100, GlobalConstants.DEFAULT_CURRENCY);
        assertEquals(ConvertUtil.convertDollarsToCents(money), expected);
    }

    @Test
    public void convertDollarsToCentsFalseTest() {
        Money money = Money.of(1.0, GlobalConstants.DEFAULT_CURRENCY);
        Money expected = Money.of(50000, GlobalConstants.DEFAULT_CURRENCY);
        assertNotEquals(ConvertUtil.convertDollarsToCents(money), expected);
    }
}
