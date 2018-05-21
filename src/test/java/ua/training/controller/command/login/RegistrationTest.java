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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class RegistrationTest {

    private Registration registrationCommand;
    private UserService userService;
    private HttpServletRequest request;
    private HttpSession session;
    private String login;
    private String password;
    private String email;
    private String wrongPassword;
    private User.Role role;
    private String language;

    @Before
    public void init() {
        userService = mock(UserService.class);
        registrationCommand = new Registration(userService);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        login = "pavloIvanovich98";
        password = "09042606Pav";
        wrongPassword = "09042606Pav1";
        email = "royd@mail.ru";
        role = User.Role.USER;
        language = "en";
    }


    @Test
    public void successfulRegistration() {
        when(request.getParameter(AttributeNames.LOGIN)).thenReturn(login);
        when(request.getParameter(AttributeNames.PASSWORD)).thenReturn(password);
        when(request.getParameter(AttributeNames.EMAIL)).thenReturn(email);
        when(request.getParameter(AttributeNames.CONFIRM_PASSWORD)).thenReturn(password);


        doNothing().when(userService).create(login, password, role, email);

        String page = registrationCommand.execute(request);
        assertNotNull(page);
        assertEquals(page, PageURLs.REDIRECT_LOGIN);
    }

    @Test
    public void passwordDontMatch() {
        when(request.getParameter(AttributeNames.LOGIN)).thenReturn(login);
        when(request.getParameter(AttributeNames.PASSWORD)).thenReturn(password);
        when(request.getParameter(AttributeNames.EMAIL)).thenReturn(email);
        when(request.getParameter(AttributeNames.CONFIRM_PASSWORD)).thenReturn(wrongPassword);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(AttributeNames.LANGUAGE)).thenReturn(language);

        doNothing().when(userService).create(login, password, role, email);

        String page = registrationCommand.execute(request);
        assertNotNull(page);
        assertEquals(page, PageURLs.REGISTRATION);
    }

    @Test
    public void userExists() {
        when(request.getParameter(AttributeNames.LOGIN)).thenReturn(login);
        when(request.getParameter(AttributeNames.PASSWORD)).thenReturn(password);
        when(request.getParameter(AttributeNames.EMAIL)).thenReturn(email);
        when(request.getParameter(AttributeNames.CONFIRM_PASSWORD)).thenReturn(password);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(AttributeNames.LANGUAGE)).thenReturn(language);
        when(userService.userExists(anyString())).thenReturn(true);

        doNothing().when(userService).create(login, password, role, email);

        String page = registrationCommand.execute(request);
        assertNotNull(page);
        assertEquals(page, PageURLs.REGISTRATION);
    }
}
