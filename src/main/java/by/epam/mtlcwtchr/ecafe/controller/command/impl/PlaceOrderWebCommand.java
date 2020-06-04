package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.entity.Order;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.command.CommandType;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlaceOrderWebCommand extends WebCommand {

    public PlaceOrderWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try {
            final Command command = Command.of(CommandType.PLACE_ORDER_COMMAND);
            final Client actor = (Client) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor");
            final Order order = actor.getCurrentOrder();
            order.setOrderDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(getRequest().getParameter("orderDate").replaceAll("T", " ")));
            command.initParams(order);
            command.execute();
            if (command.getCommandResult().first()) {
                actor.addOrder((Order) command.getCommandResult().get());
                actor.setCurrentOrder(new Order(actor));
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/home");
        } catch (ServiceException | IOException | ParseException ex) {
            throw new ControllerException(ex);
        }
    }

}
