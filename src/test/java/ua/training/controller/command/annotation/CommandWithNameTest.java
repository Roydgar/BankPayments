package ua.training.controller.command.annotation;

import org.junit.Before;
import org.junit.Test;
import ua.training.controller.annotation.CommandWithName;
import ua.training.controller.command.login.Logout;
import ua.training.controller.command.user.ShowAccounts;
import ua.training.model.service.AccountService;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.CommandNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandWithNameTest {

    private Logout logoutCommand;

    @Before
    public void init() {
        logoutCommand = new Logout();
    }

    @Test
    public void execute() {
        Class clazz = logoutCommand.getClass();
        CommandWithName annotation = (CommandWithName) clazz.getAnnotation(CommandWithName.class);
        String location = annotation.name();
        assertEquals(CommandNames.LOGOUT, location);
    }
}
