package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
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

public class ChangeProfileWebCommand extends WebCommand {

    public ChangeProfileWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            final HttpSession session = ((HttpServletRequest) getRequest()).getSession();
            final Client actor = (Client) session.getAttribute("actor");
            if (Objects.nonNull(getRequest().getParameter("name"))) {
                actor.setName(getRequest().getParameter("name"));
            }
            if (Objects.nonNull(getRequest().getParameter("phone"))) {
                actor.getUser().setPhone(getRequest().getParameter("phone"));
            }
            if (Objects.nonNull(getRequest().getParameter("email"))) {
                actor.getUser().setEmail(getRequest().getParameter("email"));
            }
            final Command changeProfileCommand = Command.of(CommandType.UPDATE_CLIENT_COMMAND);
            changeProfileCommand.initParams(actor);
            changeProfileCommand.execute();
            if(changeProfileCommand.getCommandResult().first()){
                session.removeAttribute("actor");
                session.setAttribute("actor", changeProfileCommand.getCommandResult().get());
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/profile");
        } catch ( ServiceException | IOException ex) {
            executeGet();
        }
    }

}
