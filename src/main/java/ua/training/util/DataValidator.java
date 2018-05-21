package ua.training.util;

import ua.training.util.constants.Regex;

public class DataValidator {
    public static boolean parameterIsEmptyOrNull(String ... parameters) {
        for (String parameter : parameters) {
            if (parameter == null || parameter.equals("")) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkUserInput(String login, String password) {
        return login.matches(Regex.LOGIN) && password.matches(Regex.PASSWORD);
    }

    public static boolean checkUserInput(String login, String password, String email) {
        return login.matches(Regex.LOGIN) && password.matches(Regex.PASSWORD)
                && email.matches(Regex.EMAIL);
    }
}
