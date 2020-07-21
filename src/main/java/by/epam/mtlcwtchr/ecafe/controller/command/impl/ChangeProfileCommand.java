package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ChangeProfileCommand extends Command {

    public ChangeProfileCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            final Client actor = (Client)((HttpServletRequest) getRequest()).getSession().getAttribute("actor");
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if (Objects.nonNull(getRequest().getParameter("name"))) {
                actor.setName(getRequest().getParameter("name"));
            }
            if (Objects.nonNull(getRequest().getParameter("phone"))) {
                actor.getUser().setPhone(getRequest().getParameter("phone"));
            }
            if (Objects.nonNull(getRequest().getParameter("email"))) {
                actor.getUser().setEmail(getRequest().getParameter("email"));
            }
            if (Objects.nonNull(getRequest().getParameter("password"))) {
                actor.getUser().setEmail(getRequest().getParameter("password"));
            }
            EntityServiceFactory.getInstance().getClientService().update(actor);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/profile");
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
