package ua.training.controller.command.user;

import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import ua.training.controller.command.login.Login;
import ua.training.model.entity.Account;
import ua.training.model.entity.User;
import ua.training.model.service.AccountService;
import ua.training.model.service.OperationService;
import ua.training.model.service.UserService;
import ua.training.util.AccountUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DoOperationTest {

    private DoOperation doOperationCommand;
    private OperationService operationService;
    private AccountService accountService;
    private HttpServletRequest request;
    private HttpSession session;
    private String payerAccountNumber;
    private String recipientAccountNumber;
    private Account account;
    private Money balance;
    private Money insufficientBalance;
    private String moneyAmountParameter;
    private String language;
    int userId;

    @Before
    public void init() {
        operationService = mock(OperationService.class);
        accountService = mock(AccountService.class);
        doOperationCommand = new DoOperation(operationService, accountService);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        balance = AccountUtil.getMoneyInDefaultCurrency(100000);
        insufficientBalance = AccountUtil.getMoneyInDefaultCurrency(0);
        moneyAmountParameter = "50";
        payerAccountNumber = "1356342563241235";
        recipientAccountNumber = "1356342563241233";
        account = mock(Account.class);
        language = "en";
        userId = 0;
    }

    @Test
    public void execute() {
        when(request.getParameter(AttributeNames.MONEY_AMOUNT)).thenReturn(moneyAmountParameter);
        when(request.getParameter(AttributeNames.PAYER_ACCOUNT)).thenReturn(payerAccountNumber);
        when(request.getParameter(AttributeNames.RECIPIENT_ACCOUNT)).thenReturn(recipientAccountNumber);

        when(accountService.findByNumber(anyString())).thenReturn(Optional.of(account));

        when(account.getBalance()).thenReturn(balance);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(AttributeNames.LANGUAGE)).thenReturn(language);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ID)).thenReturn(userId);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ROLE)).thenReturn(User.Role.USER);
        when(account.getType()).thenReturn(Account.Type.CHECKING);

        String path = doOperationCommand.execute(request);
        assertNotNull(path);
        assertEquals(path, PageURLs.REDIRECT_USER_MENU);
    }

    @Test
    public void insufficientBalance() {
        when(request.getParameter(AttributeNames.MONEY_AMOUNT)).thenReturn(moneyAmountParameter);
        when(request.getParameter(AttributeNames.PAYER_ACCOUNT)).thenReturn(payerAccountNumber);
        when(request.getParameter(AttributeNames.RECIPIENT_ACCOUNT)).thenReturn(recipientAccountNumber);

        when(accountService.findByNumber(anyString())).thenReturn(Optional.of(account));

        when(account.getBalance()).thenReturn(insufficientBalance);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(AttributeNames.LANGUAGE)).thenReturn(language);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ID)).thenReturn(userId);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ROLE)).thenReturn(User.Role.USER);
        when(account.getType()).thenReturn(Account.Type.CHECKING);

        String path = doOperationCommand.execute(request);
        assertNotNull(path);
        assertEquals(path, PageURLs.DO_OPERATION);
    }

    @Test
    public void wrongRecipientNumber() {
        when(request.getParameter(AttributeNames.MONEY_AMOUNT)).thenReturn(moneyAmountParameter);
        when(request.getParameter(AttributeNames.PAYER_ACCOUNT)).thenReturn(payerAccountNumber);
        when(request.getParameter(AttributeNames.RECIPIENT_ACCOUNT)).thenReturn(recipientAccountNumber);

        when(accountService.findByNumber(anyString())).thenReturn(Optional.empty());

        when(account.getBalance()).thenReturn(insufficientBalance);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(AttributeNames.LANGUAGE)).thenReturn(language);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ID)).thenReturn(userId);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ROLE)).thenReturn(User.Role.USER);
        when(account.getType()).thenReturn(Account.Type.CHECKING);

        String path = doOperationCommand.execute(request);
        assertNotNull(path);
        assertEquals(path, PageURLs.DO_OPERATION);
    }

    @Test
    public void negativeCreditBalance() {
        when(request.getParameter(AttributeNames.MONEY_AMOUNT)).thenReturn(moneyAmountParameter);
        when(request.getParameter(AttributeNames.PAYER_ACCOUNT)).thenReturn(payerAccountNumber);
        when(request.getParameter(AttributeNames.RECIPIENT_ACCOUNT)).thenReturn(recipientAccountNumber);

        when(accountService.findByNumber(anyString())).thenReturn(Optional.of(account));

        when(account.getBalance()).thenReturn(insufficientBalance);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute(AttributeNames.LANGUAGE)).thenReturn(language);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ID)).thenReturn(userId);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ROLE)).thenReturn(User.Role.USER);
        when(account.getType()).thenReturn(Account.Type.CREDIT);

        String path = doOperationCommand.execute(request);
        assertNotNull(path);
        assertEquals(path, PageURLs.REDIRECT_USER_MENU);
    }

}
