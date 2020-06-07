package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.entity.Order;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Optional;

public class PlaceOrderCommand extends Command {

    public PlaceOrderCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try {
            final Client actor = (Client) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor");
            if (Objects.nonNull(getRequest().getParameter("offlinePayment"))) {
                final Order order = actor.getCurrentOrder();
                order.setOrderDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(getRequest().getParameter("orderDate").replaceAll("T", " ")));
                final Optional<Order> savedOrder = EntityServiceFactory.getInstance().getOrderService().save(order);
                if (savedOrder.isPresent()) {
                    actor.addOrder(savedOrder.get());
                    actor.setCurrentOrder(new Order(actor));
                }
            } else {
                actor.getCurrentOrder().setOrderDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(getRequest().getParameter("orderDate").replaceAll("T", " ")));
                int sum = 0;
                for (Meal m : actor.getCurrentOrder().getMeals()) {
                    sum+=m.getPrice();
                }
                getRequest().setAttribute("totalPrice", sum);
                getRequest().getRequestDispatcher("/WEB-INF/jsp/payment.jsp").forward(getRequest(), getResponse());
                return;
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/home");
        } catch (ServiceException | IOException | ParseException | ServletException ex) {
            throw new ControllerException(ex);
        }
    }

}
