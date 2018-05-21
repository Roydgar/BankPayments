package ua.training.util;

import ua.training.model.entity.User;
import ua.training.util.constants.PageURLs;

public class UserUtil {
    public static String getPageByRole(User.Role userRole) {
        if (userRole == User.Role.ADMIN) {
            return PageURLs.REDIRECT_ADMIN_MENU;
        } else if (userRole == User.Role.USER) {
            return PageURLs.REDIRECT_USER_MENU;
        } else {
            return PageURLs.INDEX;
        }
    }

}
