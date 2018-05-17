package ua.training.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entity.User;
import ua.training.util.LoggerMessageUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.GlobalConstants;
import ua.training.util.constants.PageURLs;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;

        final User.Role userRole = (User.Role)req.getSession().getAttribute(AttributeNames.LOGGED_USER_ROLE);
        final String userLogin   = (String)req.getSession().getAttribute(AttributeNames.LOGIN);
        final String url =  req.getRequestURI();

        if (url.contains(GlobalConstants.ADMIN_PATTERN) && userRole != User.Role.ADMIN){
            resp.sendRedirect(PageURLs.INDEX);
            LogManager.getRootLogger().warn(LoggerMessageUtil.unauthorizedAccess(userLogin, userRole));
            return;
        }
        filterChain.doFilter(req, response);
    }

    @Override
    public void destroy() {

    }
}
