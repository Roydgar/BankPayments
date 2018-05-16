package ua.training.util;

public class DataValidator {
    public static boolean parameterIsEmptyOrNull(String ... parameters) {
        for (String parameter : parameters) {
            if (parameter == null || parameter.equals("")) {
                return true;
            }
        }
        return false;
    }
}
