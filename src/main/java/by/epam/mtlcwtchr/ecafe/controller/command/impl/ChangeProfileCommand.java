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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

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
            final HttpSession session = ((HttpServletRequest) getRequest()).getSession();
            final Client actor = (Client) session.getAttribute("actor");
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
            final Optional<Client> updatedActor = EntityServiceFactory.getInstance().getClientService().update(actor);
            if(updatedActor.isPresent()){
                session.removeAttribute("actor");
                session.setAttribute("actor", updatedActor.get());
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/profile");
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
