package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Order;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class UpdateOrderCommand extends Command {

    public UpdateOrderCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            final Optional<Order> order = EntityServiceFactory.getInstance().getOrderService().find(Integer.parseInt(getRequest().getParameter("chosenOrderId")));
            if (order.isPresent()) {
                final String[] params = getRequest().getParameterValues("params");
                order.get().setPaid(Arrays.toString(params).contains("isPaid"));
                order.get().setPrepared(Arrays.toString(params).contains("isPrepared"));
                order.get().setTaken(Arrays.toString(params).contains("isTaken"));
                EntityServiceFactory.getInstance().getOrderService().update(order.get());
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/aorders");
        } catch ( ServiceException | IOException ex) {
            executeGet();
        }
    }

}
