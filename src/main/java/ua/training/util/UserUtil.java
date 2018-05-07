package ua.training.util;

import ua.training.model.entity.User;
import ua.training.util.constants.PageURLs;

public class UserUtil {
    public static String getPageByRole(User.Role userRole) {
        return userRole == User.Role.ADMIN ? PageURLs.REDIRECT_ADMIN_MENU : PageURLs.REDIRECT_USER_MENU;
    }

}
