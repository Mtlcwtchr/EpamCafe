package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class DeleteOrderCommand extends Command {

    public DeleteOrderCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            if (Objects.nonNull(getRequest().getParameter("chosenOrderId")) &&
                    !getRequest().getParameter("chosenOrderId").isBlank() &&
                    !getRequest().getParameter("chosenOrderId").isEmpty()) {
                ((Client) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).removeOrder(Integer.parseInt(getRequest().getParameter("chosenOrderId")));
                EntityServiceFactory.getInstance().getOrderService().delete(Integer.parseInt(getRequest().getParameter("chosenOrderId")));
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/client_orders");
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
