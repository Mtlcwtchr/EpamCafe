package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.entity.Order;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

public class RateOrderCommand extends Command {

    public RateOrderCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            final Optional<Order> order = EntityServiceFactory.getInstance().getOrderService().find(Integer.parseInt(getRequest().getParameter("chosenOrderId")));
            if (order.isPresent()) {
                final Client actor = (Client) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor");
                actor.removeOrder(order.get().getId());
                final String[] rating = getRequest().getParameterValues("rating");
                order.get().setClientMark(
                        Arrays.toString(rating).contains("5") ? 5 :
                                Arrays.toString(rating).contains("4") ? 4 :
                                        Arrays.toString(rating).contains("3") ? 3 :
                                                Arrays.toString(rating).contains("2") ? 2 : 1);
                order.get().setClientComment(getRequest().getParameter("clientComment"));
                final Optional<Order> updatedOrder = EntityServiceFactory.getInstance().getOrderService().update(order.get());
                updatedOrder.ifPresent(actor::addOrder);
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/client_orders");
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
