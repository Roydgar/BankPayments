package ua.training.controller.filter;

import ua.training.model.entity.User;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.GlobalConstants;
import ua.training.util.constants.PageURLs;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;

        final User.Role userRole = (User.Role)req.getSession().getAttribute(AttributeNames.LOGGED_USER_ROLE);
        final String url =  req.getRequestURI();

        if (url.contains(GlobalConstants.ADMIN_PATTERN) && userRole != User.Role.ADMIN){
            resp.sendRedirect(PageURLs.INDEX);
            return;
        }
        filterChain.doFilter(req, response);
    }

    @Override
    public void destroy() {

    }
}
