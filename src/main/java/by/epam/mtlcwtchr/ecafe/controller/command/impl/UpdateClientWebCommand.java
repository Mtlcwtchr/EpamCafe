package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.command.CommandType;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

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
            final Command getCommand = Command.of(CommandType.GET_CLIENT_COMMAND);
            getCommand.initParams(Integer.parseInt(getRequest().getParameter("chosenClientId")));
            getCommand.execute();
            if (getCommand.getCommandResult().first()) {
                final Client client = (Client) getCommand.getCommandResult().get();
                if (Objects.nonNull(getRequest().getParameter("clientLoyalty"))) {
                    client.setLoyaltyPoints(Integer.parseInt(getRequest().getParameter("clientLoyalty")));
                }
                if (Objects.nonNull(getRequest().getParameter("clientBonuses"))) {
                    client.setBonuses(Integer.parseInt(getRequest().getParameter("clientBonuses")));
                }
                if (Objects.nonNull(getRequest().getParameter("isBanned"))) {
                    client.setBanned(Boolean.parseBoolean(getRequest().getParameter("isBanned")));
                }
                final Command updateCommand = Command.of(CommandType.UPDATE_CLIENT_COMMAND);
                updateCommand.initParams(client);
                updateCommand.execute();
            }
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/aclients.jsp").forward(getRequest(), getResponse());
        } catch ( ServiceException | ServletException | IOException ex) {
            executeGet();
        }
    }

}
