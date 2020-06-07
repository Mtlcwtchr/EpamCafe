package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Actor;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

public class ProfileCommand extends Command {

    public ProfileCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            getRequest().getRequestDispatcher(
                    Objects.nonNull(((HttpServletRequest) getRequest()).getSession().getAttribute("actor")) ?
                    (((Actor)((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).isPromoted() ?
                            "/WEB-INF/jsp/admin/aprofile.jsp" :
                            "/WEB-INF/jsp/profile.jsp") :
                            "/WEB-INF/jsp/signin.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        executeGet();
    }

}
