package by.epam.mtlcwtchr.ecafe.controller.filter;

import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;



@WebFilter(urlPatterns = "/*", filterName = "SignUrlFilter")
public class SignUrlFilter implements Filter {

    private static final Set<String> PROCEEDING_URIS = new HashSet<>();

    @Override
    @ExceptionableBeingLogged
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (PROCEEDING_URIS.contains(req.getRequestURI())) {
            req.setAttribute(CommonUrlFilter.COMMAND_ATTRIBUTE, CommonUrlFilter.getCommandType(servletRequest));
            req.getRequestDispatcher(CommonUrlFilter.COMMON_SERVLET_PATH).forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        final String contextPath = filterConfig.getServletContext().getContextPath();
        PROCEEDING_URIS.add(contextPath + "/sign_in");
        PROCEEDING_URIS.add(contextPath + "/sign_up");
        PROCEEDING_URIS.add(contextPath + "/sign_out");
    }

}