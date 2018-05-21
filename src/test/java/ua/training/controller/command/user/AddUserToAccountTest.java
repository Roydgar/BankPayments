package ua.training.controller.command.user;


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


public class AddUserToAccountTest {

    private UserService userService;
    private AccountService accountService;
    private AddUserToAccount addUserToAccountCommand;
    private HttpServletRequest request;
    private HttpSession session;
    private ServletContext servletContext;
    private User.Role role;
    private Account account;
    private User user;
    private String chosenAccountNumber;
    private String chosenLogin;
    private String language;
    @Before
    public void init() {
        userService = mock(UserService.class);
        accountService = mock(AccountService.class);
        addUserToAccountCommand = new AddUserToAccount(userService, accountService);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        servletContext = mock(ServletContext.class);
        account = mock(Account.class);
        user = mock(User.class);
        role = User.Role.USER;
        chosenLogin = "user";
        chosenAccountNumber = "4023637764402698";
        language = "en";
    }


    @Test
    public void trueTest() {
        when(request.getParameter(AttributeNames.CHOSEN_ACCOUNT)).thenReturn(chosenAccountNumber);
        when(request.getParameter(AttributeNames.LOGIN)).thenReturn(chosenLogin);

        when(accountService.findByNumber(chosenAccountNumber)).thenReturn(Optional.of(account));
        when(userService.getUserByLogin(chosenLogin)).thenReturn(Optional.of(user));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ROLE)).thenReturn(role);

        String page = addUserToAccountCommand.execute(request);
        assertNotNull(page);
        assertEquals(page, PageURLs.REDIRECT_USER_MENU);
    }

    @Test
    public void wrongUserFalseTest() {
        when(request.getParameter(AttributeNames.CHOSEN_ACCOUNT)).thenReturn(chosenAccountNumber);
        when(request.getParameter(AttributeNames.LOGIN)).thenReturn(chosenLogin);

        when(accountService.findByNumber(chosenAccountNumber)).thenReturn(Optional.of(account));
        when(userService.getUserByLogin(chosenLogin)).thenReturn(Optional.empty());
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ROLE)).thenReturn(role);
        when(request.getSession().getAttribute(AttributeNames.LANGUAGE)).thenReturn(language);

        String page = addUserToAccountCommand.execute(request);
        assertNotNull(page);
        assertEquals(page, PageURLs.ADD_USER_TO_ACCOUNT);
    }

    @Test
    public void wrongAccountFalseTest() {
        when(request.getParameter(AttributeNames.CHOSEN_ACCOUNT)).thenReturn(chosenAccountNumber);
        when(request.getParameter(AttributeNames.LOGIN)).thenReturn(chosenLogin);

        when(accountService.findByNumber(chosenAccountNumber)).thenReturn(Optional.empty());
        when(userService.getUserByLogin(chosenLogin)).thenReturn(Optional.of(user));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ROLE)).thenReturn(role);
        when(request.getSession().getAttribute(AttributeNames.LANGUAGE)).thenReturn(language);

        String page = addUserToAccountCommand.execute(request);
        assertNotNull(page);
        assertEquals(page, PageURLs.ADD_USER_TO_ACCOUNT);
    }


}

