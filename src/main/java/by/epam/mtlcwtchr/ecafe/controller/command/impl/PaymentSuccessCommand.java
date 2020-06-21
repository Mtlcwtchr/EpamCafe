package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.entity.Entity;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.entity.Order;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Optional;

public class PaymentSuccessCommand extends Command {

    public PaymentSuccessCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        executePost();
    }

    @Override
    public void executePost() throws ControllerException {
        try {
            final Client actor = (Client) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor");
            final Order order = actor.getCurrentOrder();
            order.setPaid(true);
            final Optional<Order> savedOrder = EntityServiceFactory.getInstance().getOrderService().save(order);
            if (savedOrder.isPresent()) {
                actor.setBonuses(actor.getBonuses() + savedOrder.get().getMeals().stream().mapToInt(Meal::getPrice).sum()/2);
                savedOrder.ifPresent(actor::addOrder);
                actor.setCurrentOrder(new Order(actor));
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/profile?success=true");
        } catch (ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
