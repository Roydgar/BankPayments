package ua.training.controller.command.filter;

import org.apache.logging.log4j.LogManager;
import org.junit.Before;
import org.junit.Test;
import ua.training.controller.filter.AuthFilter;
import ua.training.controller.filter.EncodingFilter;
import ua.training.model.entity.User;
import ua.training.model.service.CreditRequestService;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class EncodingFilterTest {
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
        filter = new EncodingFilter();
        session = mock(HttpSession.class);
    }


    @Test
    public void execute() {
        try {
            filter.doFilter(request, response, filterChain);
            filterChain.doFilter(request,response);
            verify(filterChain, atLeast(1)).doFilter(request, response);
        } catch (Exception e) {
            LogManager.getRootLogger().error(e);
        }
    }
}
