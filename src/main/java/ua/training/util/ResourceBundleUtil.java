package ua.training.util;

import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PropertyFileNames;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleUtil {

    private static String getPropertyFromLangBundle(String property, Locale lang) {
        ResourceBundle langBundle = ResourceBundle.getBundle(PropertyFileNames.LOCALE, lang);
        return langBundle.getString(property);
    }

    public static void setErrorMessage(HttpServletRequest request, String message) {
        Locale locale = new Locale((String)request.getSession().getAttribute(AttributeNames.LANGUAGE));

        request.setAttribute(AttributeNames.WRONG_INPUT_MESSAGE, ResourceBundleUtil.
                getPropertyFromLangBundle(message, locale));
    }

}
