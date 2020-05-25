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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInWebCommand extends WebCommand {

    public SignInWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            getRequest().getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(getRequest(), getResponse());
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
                    getRequest().getParameter("password"));
            command.execute();
            if (command.getCommandResult().first()) {
                final HttpSession session = ((HttpServletRequest) getRequest()).getSession();
                session.setAttribute("actor", command.getCommandResult().get());
            }
            getRequest().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(getRequest(), getResponse());
        } catch (ServiceException | ServletException | IOException ex) {
            executeGet();
        }
    }

}