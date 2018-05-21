package ua.training.controller.command.filter;

import org.apache.logging.log4j.LogManager;
import org.junit.Before;
import org.junit.Test;
import ua.training.controller.command.admin.ShowCreditRequests;
import ua.training.controller.filter.AuthFilter;
import ua.training.model.entity.User;
import ua.training.model.service.CreditRequestService;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.*;

public class AuthFilterTest {
    private Filter filter;
    private FilterChain filterChain;
    private CreditRequestService creditRequestService;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @Before
    public void init() {
        filterChain = mock(FilterChain.class);
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        filter = new AuthFilter();
        session = mock(HttpSession.class);
    }


    @Test
    public void authorizedAccess() {
        when(request.getSession()).thenReturn(session);
        when(request.getRequestURI()).thenReturn(PageURLs.ADMIN_MENU);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ROLE)).thenReturn(User.Role.ADMIN);

        try {
            filter.doFilter(request, response, filterChain);
            filterChain.doFilter(request,response);
            verify(response, times(0)).sendRedirect(anyString());
        } catch (Exception e) {
            LogManager.getRootLogger().error(e);
        }
    }

    @Test
    public void unauthorizedAccess() {
        when(request.getSession()).thenReturn(session);
        when(request.getRequestURI()).thenReturn(PageURLs.ADMIN_MENU);
        when(session.getAttribute(AttributeNames.LOGGED_USER_ROLE)).thenReturn(User.Role.USER);

        try {
            filter.doFilter(request, response, filterChain);
            filterChain.doFilter(request,response);
            verify(response, times(1)).sendRedirect(anyString());
        } catch (Exception e) {
            LogManager.getRootLogger().error(e);
        }
    }

}
