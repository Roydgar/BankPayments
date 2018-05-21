package ua.training.util;

import org.junit.Test;
import ua.training.model.entity.User;
import ua.training.util.constants.PageURLs;

import static org.junit.Assert.assertEquals;

public class UserUtilTest {

    @Test
    public void adminRole() {
        assertEquals(UserUtil.getPageByRole(User.Role.ADMIN), PageURLs.REDIRECT_ADMIN_MENU);
    }

    @Test
    public void userRole() {
        assertEquals(UserUtil.getPageByRole(User.Role.USER), PageURLs.REDIRECT_USER_MENU);
    }

    @Test
    public void unknownRole() {
        assertEquals(UserUtil.getPageByRole(User.Role.UNKNOWN), PageURLs.INDEX);
    }
}
