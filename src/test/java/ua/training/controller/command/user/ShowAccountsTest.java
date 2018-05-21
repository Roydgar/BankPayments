package ua.training.controller.command.user;

import org.junit.Before;
import org.junit.Test;
import ua.training.controller.command.login.Logout;
import ua.training.model.service.AccountService;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowAccountsTest {

    private ShowAccounts showAccountsCommand;
    private AccountService accountService;
    private HttpServletRequest request;
    private HttpSession session;
    private ServletContext servletContext;
    private int userId;
    @Before
    public void init() {
        accountService = mock(AccountService.class);
        showAccountsCommand = new ShowAccounts(accountService);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        servletContext = mock(ServletContext.class);
        userId = 0;
    }


    @Test
    public void successfulRegistration() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ID)).thenReturn(userId);

        String page = showAccountsCommand.execute(request);
        assertNotNull(page);
        assertEquals(page, PageURLs.ACCOUNT_INFO);
    }
}
