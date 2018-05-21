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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class LoginTest {

    private Login loginCommand;
    private UserService userService;
    private AccountService accountService;
    private HttpServletRequest request;
    private HttpSession session;
    private User user;
    private String login;
    private String password;
    private String wrongPassword;
    private Integer id;
    private User.Role role;


    @Before
    public void init() {
        userService = mock(UserService.class);
        accountService = mock(AccountService.class);
        loginCommand = new Login(userService, accountService);
        user = mock(User.class);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        login = "pavloIvanovich98";
        password = "09042606Pav";
        id = 8;                     //check id
        role = User.Role.USER;
    }


    @Test
    public void execute(){
        when(request.getParameter(AttributeNames.LOGIN)).thenReturn(login);
        when(request.getParameter(AttributeNames.PASSWORD)).thenReturn(password);

        when(userService.login(anyString(), anyString())).thenReturn(Optional.of(user));

        when(user.getId()).thenReturn(id);
        when(user.getLogin()).thenReturn(login);
        when(user.getPassword()).thenReturn(password);
        when(user.getRole()).thenReturn(role);
        when(accountService.findAccountsByUserId(id)).thenReturn(anyList());
        when(session.getAttribute(AttributeNames.LOGGED_USER_ROLE)).thenReturn(role);

        when(request.getSession()).thenReturn(session);

        String page = loginCommand.execute(request);
        assertNotNull(page);
        assertEquals(page, PageURLs.REDIRECT_USER_MENU);
    }

    @Test
    public void userIsLogged(){
        when(request.getParameter(AttributeNames.LOGIN)).thenReturn(login);
        when(request.getParameter(AttributeNames.PASSWORD)).thenReturn(password);

        when(userService.login(anyString(), anyString())).thenReturn(Optional.of(user));

        when(user.getId()).thenReturn(id);
        when(user.getLogin()).thenReturn(login);
        when(user.getPassword()).thenReturn(password);
        when(user.getRole()).thenReturn(role);
        when(accountService.findAccountsByUserId(id)).thenReturn(anyList());
        when(session.getAttribute(AttributeNames.LOGGED_USER_ROLE)).thenReturn(role);
        when(userService.userIsLogged(request, login)).thenReturn(true);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(AttributeNames.LANGUAGE)).thenReturn("en");

        String page = loginCommand.execute(request);

        assertNotNull(page);
        assertEquals(page, PageURLs.ERROR);
    }

    @Test
    public void wrongPassword() throws Exception {
        when(request.getParameter(AttributeNames.LOGIN)).thenReturn(login);
        when(request.getParameter(AttributeNames.PASSWORD)).thenReturn(password);

        when(userService.login(anyString(), anyString())).thenReturn(Optional.empty());

        when(user.getId()).thenReturn(id);
        when(user.getLogin()).thenReturn(login);
        when(user.getPassword()).thenReturn(password);
        when(user.getRole()).thenReturn(role);
        when(accountService.findAccountsByUserId(id)).thenReturn(anyList());
        when(session.getAttribute(AttributeNames.LOGGED_USER_ROLE)).thenReturn(role);
        when(userService.userIsLogged(request, login)).thenReturn(true);

        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(AttributeNames.LANGUAGE)).thenReturn("en");

        String page = loginCommand.execute(request);

        assertNotNull(page);
        assertEquals(page, PageURLs.ERROR);
    }
}
