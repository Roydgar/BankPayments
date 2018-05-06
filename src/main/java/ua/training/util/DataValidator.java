package ua.training.util;

public class DataValidator {
    public static boolean parameterIsEmptyOrNull(String ... parameters) {
        for (String parameter: parameters ) {
            if (parameter == null || parameter.equals("")) {
                return true;
            }
        }
        return false;
    }

    public static boolean passwordsAreEquals(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
