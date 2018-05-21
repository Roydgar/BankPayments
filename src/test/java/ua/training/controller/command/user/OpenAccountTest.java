package ua.training.controller.command.user;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ua.training.model.entity.Account;
import ua.training.model.entity.CreditRequest;
import ua.training.model.entity.User;
import ua.training.model.service.AccountService;
import ua.training.model.service.CreditRequestService;
import ua.training.model.service.UserService;
import ua.training.util.AccountUtil;
import ua.training.util.DataValidator;
import ua.training.util.constants.AccountConstants;
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

public class OpenAccountTest {

    private OpenAccount openAccountCommand;
    private AccountService accountService;
    private CreditRequestService creditRequestService;
    private HttpServletRequest request;
    private HttpSession session;
    private String moneyAmountParameter;
    private int userId;
    private Account.Type type;
    private User.Role role;
    private String language;

    @Before
    public void init(){
        accountService = mock(AccountService.class);
        creditRequestService = mock(CreditRequestService.class);
        openAccountCommand = new OpenAccount(accountService, creditRequestService);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        type = Account.Type.CHECKING;
        moneyAmountParameter = "100";
        userId = 0;
        role = User.Role.USER;
        language = "en";
    }

    @Test
    public void execute() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(AttributeNames.ACCOUNT_TYPE)).thenReturn(type.toString());
        when(request.getParameter(AttributeNames.MONEY_AMOUNT)).thenReturn(moneyAmountParameter);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ID)).thenReturn(userId);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ROLE)).thenReturn(role);

        String path = openAccountCommand.execute(request);
        assertNotNull(path);
        Assert.assertEquals(path, PageURLs.REDIRECT_USER_MENU);
        verify(accountService, times(1)).create(anyObject(), anyInt(), anyObject());
    }

    @Test
    public void createCreditRequest() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(AttributeNames.ACCOUNT_TYPE)).thenReturn(Account.Type.CREDIT.toString());
        when(request.getParameter(AttributeNames.MONEY_AMOUNT)).thenReturn(moneyAmountParameter);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ID)).thenReturn(userId);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ROLE)).thenReturn(role);


        String path = openAccountCommand.execute(request);
        assertNotNull(path);
        Assert.assertEquals(path, PageURLs.REDIRECT_USER_MENU);

        verify(creditRequestService, times(1)).create(anyInt(), anyString(), anyObject());
    }

    @Test
    public void moneyIsBiggerThanLimit() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(AttributeNames.ACCOUNT_TYPE)).thenReturn(type.toString());
        when(request.getParameter(AttributeNames.MONEY_AMOUNT)).thenReturn(
                String.valueOf(AccountConstants.CHECKING_BALANCE_LIMIT + 1));
        when(session.getAttribute(AttributeNames.LOGGED_USER_ID)).thenReturn(userId);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ROLE)).thenReturn(role);
        when(request.getSession().getAttribute(AttributeNames.LANGUAGE)).thenReturn(language);

        String path = openAccountCommand.execute(request);
        assertNotNull(path);
        Assert.assertEquals(path, PageURLs.OPEN_ACCOUNT);

        verify(accountService, times(0)).create(anyObject(), anyInt(), anyObject());
        verify(creditRequestService, times(0)).create(anyInt(), anyString(), anyObject());
    }
}
