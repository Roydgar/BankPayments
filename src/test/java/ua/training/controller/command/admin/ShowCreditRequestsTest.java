package ua.training.controller.command.admin;

import org.junit.Before;
import org.junit.Test;
import ua.training.controller.command.user.ShowAccounts;
import ua.training.model.service.AccountService;
import ua.training.model.service.CreditRequestService;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowCreditRequestsTest {

    private ShowCreditRequests showCreditRequests;
    private CreditRequestService creditRequestService;
    private HttpServletRequest request;
    private HttpSession session;
    private ServletContext servletContext;
    private int userId;
    @Before
    public void init() {
        creditRequestService = mock(CreditRequestService.class);
        showCreditRequests = new ShowCreditRequests(creditRequestService);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        servletContext = mock(ServletContext.class);
        userId = 0;
    }


    @Test
    public void successfulRegistration() {
        when(request.getSession()).thenReturn(session);

        String page = showCreditRequests.execute(request);
        assertNotNull(page);
        assertEquals(page, PageURLs.SHOW_CREDIT_REQUESTS_REDIRECT);
    }
}
