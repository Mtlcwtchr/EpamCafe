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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
            Date orderDate = new Date();
            orderDate.setHours(new SimpleDateFormat("HH").parse(getRequest().getParameter("orderTime")).getHours());
            orderDate.setMinutes(new SimpleDateFormat("mm").parse(getRequest().getParameter("orderTime")).getMinutes());
            final Client actor = (Client) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor");
            actor.getCurrentOrder().setOrderDate(orderDate);
            if (Objects.nonNull(getRequest().getParameter("offlinePayment"))) {
                final Optional<Order> savedOrder = EntityServiceFactory.getInstance().getOrderService().save(actor.getCurrentOrder());
                if (savedOrder.isPresent()) {
                    actor.addOrder(savedOrder.get());
                    actor.setCurrentOrder(new Order(actor));
                }
            } else {
                int sum = 0;
                for (Meal m : actor.getCurrentOrder().getMeals()) {
                    sum+=m.getPrice();
                }
                getRequest().setAttribute("totalPrice", sum);
                getRequest().getRequestDispatcher("/WEB-INF/jsp/payment.jsp").forward(getRequest(), getResponse());
                return;
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/profile?success=true");
        } catch (ServiceException | IOException | ParseException | ServletException ex) {
            throw new ControllerException(ex);
        }
    }

}
