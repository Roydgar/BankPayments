package ua.training.controller.command.login;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import ua.training.model.entity.Account;
import ua.training.model.entity.User;
import ua.training.model.service.AccountService;
import ua.training.model.service.UserService;
import ua.training.util.DataValidator;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


public class LogoutTest {

    private Logout logoutCommand;
    private HttpServletRequest request;
    private HttpSession session;
    private ServletContext servletContext;

    @Before
    public void init() {
        logoutCommand = new Logout();
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        servletContext = mock(ServletContext.class);
    }


    @Test
    public void successfulRegistration() {
        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(anyString())).thenReturn(new HashSet<String>());

        String page = logoutCommand.execute(request);
        assertNotNull(page);
        assertEquals(page, PageURLs.INDEX);
    }


}
