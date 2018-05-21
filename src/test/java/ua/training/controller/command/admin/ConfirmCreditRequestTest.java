package ua.training.controller.command.admin;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.training.model.entity.Account;
import ua.training.model.entity.CreditRequest;
import ua.training.model.service.AccountService;
import ua.training.model.service.CreditRequestService;
import ua.training.util.AccountUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class ConfirmCreditRequestTest {
    private ConfirmCreditRequest confirmCreditRequestCommand;
    private CreditRequestService creditRequestService;
    private AccountService accountService;
    private HttpServletRequest request;
    private HttpSession session;
    private ServletContext servletContext;
    private int userId;
    private CreditRequest creditRequest;
    private String language;
    @Before
    public void init() {
        creditRequestService = mock(CreditRequestService.class);
        accountService = mock(AccountService.class);
        confirmCreditRequestCommand = new ConfirmCreditRequest(creditRequestService, accountService);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        servletContext = mock(ServletContext.class);
        userId = 0;
        creditRequest = mock(CreditRequest.class);
        language = "en";
    }


    @Test
    public void execute() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(AttributeNames.CREDIT_REQUEST_ID)).thenReturn("0:CONFIRMED");
        when(creditRequest.getStatus()).thenReturn(CreditRequest.Status.NEW);
        when(creditRequest.getMoneyAmount()).thenReturn(AccountUtil.getMoneyInDefaultCurrency(10));
        when(creditRequestService.findById(anyInt())).thenReturn(Optional.of(creditRequest));

        String page = confirmCreditRequestCommand.execute(request);
        assertNotNull(page);
        assertEquals(page, PageURLs.SHOW_CREDIT_REQUESTS_REDIRECT);

        verify(accountService, times(1)).create(anyObject(), anyInt(), anyObject());
    }

    @Test
    public void requestDenied() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(AttributeNames.CREDIT_REQUEST_ID)).thenReturn("0:DENIED");
        when(creditRequest.getStatus()).thenReturn(CreditRequest.Status.NEW);
        when(creditRequest.getMoneyAmount()).thenReturn(AccountUtil.getMoneyInDefaultCurrency(10));
        when(creditRequestService.findById(anyInt())).thenReturn(Optional.of(creditRequest));

        String page = confirmCreditRequestCommand.execute(request);
        assertNotNull(page);
        assertEquals(page, PageURLs.SHOW_CREDIT_REQUESTS_REDIRECT);

        verify(accountService, times(0)).create(anyObject(), anyInt(), anyObject());
    }

    @Test
    public void confirmedByAnotherAdmin() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(AttributeNames.CREDIT_REQUEST_ID)).thenReturn("0:NEW");
        when(creditRequest.getStatus()).thenReturn(CreditRequest.Status.CONFIRMED);
        when(creditRequest.getMoneyAmount()).thenReturn(AccountUtil.getMoneyInDefaultCurrency(10));
        when(creditRequestService.findById(anyInt())).thenReturn(Optional.of(creditRequest));
        when(request.getSession().getAttribute(AttributeNames.LANGUAGE)).thenReturn(language);

        String page = confirmCreditRequestCommand.execute(request);
        assertNotNull(page);
        assertEquals(page, PageURLs.SHOW_CREDIT_REQUESTS);

        verify(accountService, times(0)).create(anyObject(), anyInt(), anyObject());
    }

    @Test
    public void deniedByAnotherAdmin() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(AttributeNames.CREDIT_REQUEST_ID)).thenReturn("0:NEW");
        when(creditRequest.getStatus()).thenReturn(CreditRequest.Status.DENIED);
        when(creditRequest.getMoneyAmount()).thenReturn(AccountUtil.getMoneyInDefaultCurrency(10));
        when(creditRequestService.findById(anyInt())).thenReturn(Optional.of(creditRequest));
        when(request.getSession().getAttribute(AttributeNames.LANGUAGE)).thenReturn(language);

        String page = confirmCreditRequestCommand.execute(request);
        assertNotNull(page);
        assertEquals(page, PageURLs.SHOW_CREDIT_REQUESTS);

        verify(accountService, times(0)).create(anyObject(), anyInt(), anyObject());
    }

}
