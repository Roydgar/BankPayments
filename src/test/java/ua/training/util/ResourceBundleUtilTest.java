package ua.training.util;

import org.junit.Test;
import ua.training.util.constants.ResponseMessages;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ResourceBundleUtilTest {
    @Test
    public void getPropertyFromLangBundleTrueTest() {
        assertEquals(ResourceBundleUtil.getPropertyFromLangBundle(ResponseMessages.LOGIN_ERROR,
                new Locale("en")), "Wrong login or password.");
    }

    @Test
    public void getPropertyFromLangBundleFalseTest() {
        assertNotEquals(ResourceBundleUtil.getPropertyFromLangBundle(ResponseMessages.LOGIN_ERROR,
                new Locale("en")), "Random string");
    }
}
