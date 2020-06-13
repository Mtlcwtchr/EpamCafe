package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class UpdateClientCommand extends Command {

    public UpdateClientCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            final Optional<Client> client = EntityServiceFactory.getInstance().getClientService().find(Integer.parseInt(getRequest().getParameter("chosenClientId")));
            if (client.isPresent()) {
                if (Objects.nonNull(getRequest().getParameter("clientLoyalty"))) {
                    client.get().setLoyaltyPoints(Integer.parseInt(getRequest().getParameter("clientLoyalty").replaceAll(" ", "")));
                }
                if (Objects.nonNull(getRequest().getParameter("clientBonuses"))) {
                    client.get().setBonuses(Integer.parseInt(getRequest().getParameter("clientBonuses").replaceAll(" ", "")));
                }
                client.get().setBanned(Arrays.toString(getRequest().getParameterValues("params")).contains("isBanned"));
                EntityServiceFactory.getInstance().getClientService().update(client.get());
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/aclients?open=" + getRequest().getParameter("chosenClientId"));
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
