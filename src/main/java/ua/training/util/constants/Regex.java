package ua.training.util.constants;

import java.util.ResourceBundle;

public interface Regex {
    ResourceBundle regexBundle = ResourceBundle.getBundle(PropertyFileNames.REGEX);

    String LOGIN    = regexBundle.getString("login");
    String PASSWORD = regexBundle.getString("password");
    String EMAIL    = regexBundle.getString("email");
}
