package by.epam.mtlcwtchr.ecafe.controller.command.impl.localisationservice;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class LocalisationService {

    public static void setLocale(ServletRequest request, ServletResponse response) {
        ((HttpServletRequest)request).getSession().removeAttribute("locale");
        ((HttpServletRequest)request).getSession().setAttribute("locale", request.getParameter("locale"));
        ((HttpServletResponse) response).addCookie(new Cookie("locale", request.getParameter("locale")));
    }
}
