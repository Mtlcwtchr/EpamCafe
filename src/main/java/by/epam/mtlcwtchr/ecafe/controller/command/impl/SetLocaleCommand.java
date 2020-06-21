package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetLocaleCommand extends Command {

    public SetLocaleCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        executePost();
    }

    @Override
    public void executePost() throws ControllerException {
        try {
            ((HttpServletRequest) getRequest()).getSession().removeAttribute("locale");
            ((HttpServletRequest) getRequest()).getSession().setAttribute("locale", getRequest().getParameter("locale"));
            ((HttpServletResponse) getResponse()).addCookie(new Cookie("locale", getRequest().getParameter("locale")));
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/");
        } catch (IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
