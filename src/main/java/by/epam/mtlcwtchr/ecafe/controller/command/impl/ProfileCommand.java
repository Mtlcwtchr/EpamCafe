package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Actor;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.entity.Order;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class ProfileCommand extends Command {

    public ProfileCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            if (Objects.nonNull(((HttpServletRequest) getRequest()).getSession().getAttribute("actor")) &&
                    !(((Actor)((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).isPromoted())) {
                final Order currentOrder = ((Client)((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).getCurrentOrder();
                final Optional<Client> actor = EntityServiceFactory.getInstance().getClientService().find(
                        ((Actor)((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).getId());
                ((HttpServletRequest)getRequest()).getSession().removeAttribute("actor");
                if (actor.isPresent()) {
                    if (actor.get().isBanned()) {
                        getRequest().getRequestDispatcher("/WEB-INF/jsp/bannedinfopage.jsp").forward(getRequest(), getResponse());
                        return;
                    }
                    actor.get().setCurrentOrder(currentOrder);
                    ((HttpServletRequest) getRequest()).getSession().setAttribute("actor", actor.get());
                }
            }
            getRequest().getRequestDispatcher(
                    Objects.nonNull(((HttpServletRequest) getRequest()).getSession().getAttribute("actor")) ?
                    (((Actor)((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).isPromoted() ?
                            "/WEB-INF/jsp/admin/adminprofile.jsp" :
                            "/WEB-INF/jsp/profile.jsp") :
                            "/WEB-INF/jsp/signin.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        executeGet();
    }

}
