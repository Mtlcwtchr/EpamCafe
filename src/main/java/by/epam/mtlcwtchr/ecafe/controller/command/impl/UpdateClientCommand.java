package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.AdminCommand;
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

public class UpdateClientCommand extends AdminCommand {

    public UpdateClientCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if(Objects.nonNull(getRequest().getParameter("ukey")) &&
                    !getRequest().getParameter("ukey").isEmpty() &&
                    !getRequest().getParameter("ukey").isBlank() &&
                    getRequest().getParameter("ukey").matches("[0-9]++")) {
                final Optional<Client> client = EntityServiceFactory.getInstance().getClientService().find(Integer.parseInt(getRequest().getParameter("ukey")));
                if (client.isPresent()) {
                    if (Objects.nonNull(getRequest().getParameter("clientLoyalty"))) {
                        client.get().setLoyaltyPoints(Integer.parseInt(getRequest().getParameter("clientLoyalty").replaceAll(" ", "")));
                    } else {
                        WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                    }
                    if (Objects.nonNull(getRequest().getParameter("clientBonuses"))) {
                        client.get().setBonuses(Integer.parseInt(getRequest().getParameter("clientBonuses").replaceAll(" ", "")));
                    } else {
                        WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                    }
                    client.get().setBanned(Arrays.toString(getRequest().getParameterValues("params")).contains("isBanned"));
                    EntityServiceFactory.getInstance().getClientService().update(client.get());
                }
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() +
                        (Objects.nonNull(getRequest().getParameter("backToCurrent")) ?
                                "/admin_client_info?key=" :
                                "/admin_clients?open=") + getRequest().getParameter("ukey"));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
