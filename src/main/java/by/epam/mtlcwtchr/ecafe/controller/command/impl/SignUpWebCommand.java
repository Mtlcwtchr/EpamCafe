package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.command.WebCommandType;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.command.CommandType;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class SignUpWebCommand extends WebCommand {

    public SignUpWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            getRequest().getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        try {
            final Command command = Command.of(CommandType.AUTHORIZATE_COMMAND);
            command.initParams(
                    getRequest().getParameter("username"),
                    getRequest().getParameter("password"),
                    getRequest().getParameter("email"),
                    getRequest().getParameter("phone"),
                    getRequest().getParameter("name"));
            command.execute();
            if (command.getCommandResult().first()) {
                final HttpSession session = ((HttpServletRequest) getRequest()).getSession();
                session.setAttribute("actor", command.getCommandResult().get());
            }
            WebCommand.of(WebCommandType.HOME, getRequest(), getResponse()).executeGet();
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
