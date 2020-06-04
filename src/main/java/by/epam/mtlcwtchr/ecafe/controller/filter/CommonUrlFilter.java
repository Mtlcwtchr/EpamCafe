package by.epam.mtlcwtchr.ecafe.controller.filter;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommandType;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@WebFilter(urlPatterns = "/*", filterName = "CommonUrlFilter")
public class CommonUrlFilter implements Filter {

    public static final Set<String> PROCEEDING_URIS = new HashSet<>();
    public static final String COMMON_SERVLET_PATH = "/app";
    public static final String COMMAND_ATTRIBUTE = "command";

    @Override
    @ExceptionableBeingLogged
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        req.setAttribute("time", new Date());
        if (PROCEEDING_URIS.contains(req.getRequestURI())) {
            req.setAttribute(COMMAND_ATTRIBUTE, getCommandType(servletRequest));
            req.getRequestDispatcher(COMMON_SERVLET_PATH).forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        final String contextPath = filterConfig.getServletContext().getContextPath();
        PROCEEDING_URIS.add(contextPath + "/");
        PROCEEDING_URIS.add(contextPath + "/home");
        PROCEEDING_URIS.add(contextPath + "/profile");
        PROCEEDING_URIS.add(contextPath + "/change_profile");
        PROCEEDING_URIS.add(contextPath + "/meals");
        PROCEEDING_URIS.add(contextPath + "/ingredients");
        PROCEEDING_URIS.add(contextPath + "/categories");
        PROCEEDING_URIS.add(contextPath + "/client_order");
        PROCEEDING_URIS.add(contextPath + "/add_meal_to_order");
        PROCEEDING_URIS.add(contextPath + "/remove_meal_from_order");
        PROCEEDING_URIS.add(contextPath + "/client_order");
        PROCEEDING_URIS.add(contextPath + "/client_orders");
        PROCEEDING_URIS.add(contextPath + "/payment");
        PROCEEDING_URIS.add(contextPath + "/place_order");
        PROCEEDING_URIS.add(contextPath + "/aorders");
    }

    private WebCommandType getCommandType(ServletRequest request) {
        if(((HttpServletRequest)request).getRequestURI().equals(((HttpServletRequest) request).getContextPath()+"/")){
            return WebCommandType.HOME_COMMAND;
        }
        return WebCommandType.valueOf(
                ((HttpServletRequest) request).getRequestURI()
                        .substring(request.getServletContext().getContextPath().length())
                        .replaceAll("/", "")
                        .concat("_command")
                        .toUpperCase());
    }

}