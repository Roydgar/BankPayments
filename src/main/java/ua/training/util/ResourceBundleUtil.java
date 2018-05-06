package ua.training.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleUtil {
    public static String getMessage(String message, Locale lang) {
        ResourceBundle bundle = ResourceBundle.getBundle("locale", lang);
        return bundle.getString(message);
    }
}
