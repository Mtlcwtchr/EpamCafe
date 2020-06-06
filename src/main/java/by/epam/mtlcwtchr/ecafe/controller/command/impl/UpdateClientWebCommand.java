package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.entity.Entity;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.command.CommandType;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class UpdateClientWebCommand extends WebCommand {

    public UpdateClientWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            final Optional<Client> client = EntityServiceFactory.getInstance().getClientService().find(Integer.parseInt(getRequest().getParameter("chosenClientId")));
            if (client.isPresent()) {
                if (Objects.nonNull(getRequest().getParameter("clientLoyalty"))) {
                    client.get().setLoyaltyPoints(Integer.parseInt(getRequest().getParameter("clientLoyalty").replaceAll(" ", "")));
                }
                if (Objects.nonNull(getRequest().getParameter("clientBonuses"))) {
                    client.get().setBonuses(Integer.parseInt(getRequest().getParameter("clientBonuses").replaceAll(" ", "")));
                }
                if (Objects.nonNull(getRequest().getParameter("isBanned"))) {
                    client.get().setBanned(Boolean.parseBoolean(getRequest().getParameter("isBanned")));
                }
                EntityServiceFactory.getInstance().getClientService().update(client.get());
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/aclients?open=" + getRequest().getParameter("chosenClientId"));
        } catch ( ServiceException | IOException ex) {
            executeGet();
        }
    }

}
