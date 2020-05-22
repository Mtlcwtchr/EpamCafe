package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

public class JoinWebCommand extends WebCommand {

    public JoinWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try {
            getRequest()
                    .getRequestDispatcher(Objects.isNull(
                            ((HttpServletRequest) getRequest()).getSession().getAttribute("actor")) ?
                            "/WEB-INF/jsp/authorization.jsp" : "/WEB-INF/jsp/home.jsp")
                    .forward(getRequest(), getResponse());
        } catch (ServletException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
