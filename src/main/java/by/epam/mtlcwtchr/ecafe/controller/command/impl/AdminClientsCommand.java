package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.AdminCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class AdminClientsCommand extends AdminCommand {

    public AdminClientsCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try {
            getRequest().setAttribute("clients",
                    EntityServiceFactory.getInstance().getClientService().getList());
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/adminclients.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
