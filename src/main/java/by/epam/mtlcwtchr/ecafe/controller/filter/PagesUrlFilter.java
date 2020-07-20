package by.epam.mtlcwtchr.ecafe.controller.filter;

import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@WebFilter(urlPatterns = "/*", filterName = "PagesUrlFilter")
public class PagesUrlFilter implements Filter {

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
        Collections.addAll(PROCEEDING_URIS,
                /* Client page urls */
                contextPath + "/about_cafe",
                contextPath + "/manual",
                contextPath + "/payment",
                contextPath + "/client_order",
                contextPath + "/menu",
                contextPath + "/halls",
                contextPath + "/meal_info",
                contextPath + "/order_info",
                /* Admin page urls */
                contextPath + "/admin_menu",
                contextPath + "/admin_categories",
                contextPath + "/admin_ingredients",
                contextPath + "/admin_orders",
                contextPath + "/admin_halls",
                contextPath + "/admin_clients",
                contextPath + "/active_orders",
                contextPath + "/admin_reviews",
                /* Both actors page urls */
                contextPath + "/",
                contextPath + "/home",
                contextPath + "/profile",
                contextPath + "/reservation",
                contextPath + "/client_orders",
                contextPath + "/something_went_wrong"
                );
    }

}