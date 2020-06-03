package by.epam.mtlcwtchr.ecafe.controller.filter;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommandType;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;


@WebFilter(urlPatterns = "/load_image")
public class PictureUrlFilter implements Filter {

    @Override
    @ExceptionableBeingLogged
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setAttribute("imageUrl", servletRequest.getParameter("url"));
        servletRequest.setAttribute(CommonUrlFilter.COMMAND_ATTRIBUTE, getCommandType(servletRequest));
        servletRequest.getRequestDispatcher(CommonUrlFilter.COMMON_SERVLET_PATH).forward(servletRequest, servletResponse);
    }

    private WebCommandType getCommandType(ServletRequest request) {
        return WebCommandType.valueOf(
                ((HttpServletRequest) request).getRequestURI()
                        .substring(request.getServletContext().getContextPath().length())
                        .replaceAll("/", "")
                        .concat("_command")
                        .toUpperCase());
    }

}