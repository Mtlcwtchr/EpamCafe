package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Admin;
import by.epam.mtlcwtchr.ecafe.entity.User;
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

public class ChangeAdminProfileCommand extends Command {

    public ChangeAdminProfileCommand(ServletRequest request, ServletResponse response){
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
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if (Objects.nonNull(getRequest().getParameter("username"))) {
                actor.getUser().setUsername(getRequest().getParameter("username"));
            }
            if (Objects.nonNull(getRequest().getParameter("password"))) {
                actor.getUser().setPassword(getRequest().getParameter("password"));
            }
            final Optional<User> updatedUser = EntityServiceFactory.getInstance().getUserService().update(actor.getUser());
            if(updatedUser.isPresent()){
                session.removeAttribute("actor");
                actor.setUser(updatedUser.get());
                session.setAttribute("actor", actor);
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/profile");
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
