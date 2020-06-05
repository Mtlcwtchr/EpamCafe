package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Admin;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.entity.User;
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

public class ChangeAdminProfileWebCommand extends WebCommand {

    public ChangeAdminProfileWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            final HttpSession session = ((HttpServletRequest) getRequest()).getSession();
            final Admin actor = (Admin) session.getAttribute("actor");
            if (Objects.nonNull(getRequest().getParameter("username"))) {
                actor.getUser().setUsername(getRequest().getParameter("username"));
            }
            if (Objects.nonNull(getRequest().getParameter("password"))) {
                actor.getUser().setPassword(getRequest().getParameter("password"));
            }
            final Command changeProfileCommand = Command.of(CommandType.UPDATE_USER_COMMAND);
            changeProfileCommand.initParams(actor.getUser());
            changeProfileCommand.execute();
            if(changeProfileCommand.getCommandResult().first()){
                session.removeAttribute("actor");
                actor.setUser((User) changeProfileCommand.getCommandResult().get());
                session.setAttribute("actor", actor);
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/profile");
        } catch ( ServiceException | IOException ex) {
            executeGet();
        }
    }

}
