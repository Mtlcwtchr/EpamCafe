package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.config.StaticDataHandler;
import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
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
import java.util.Objects;

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
            if (Objects.nonNull(getRequest().getParameter("key")) &&
                    getRequest().getParameter("key").matches("[0-9]++")) {
                ((Client)((HttpServletRequest)getRequest()).getSession().getAttribute("actor")).getOrder(Integer.parseInt(getRequest().getParameter("key")))
                        .ifPresent( order -> {
                    final String[] rating = getRequest().getParameterValues("rating");
                    order.setClientMark(
                            Arrays.toString(rating).contains("5") ? 5 :
                                    Arrays.toString(rating).contains("4") ? 4 :
                                            Arrays.toString(rating).contains("3") ? 3 :
                                                    Arrays.toString(rating).contains("2") ? 2 : 1);
                    order.setClientComment(
                            Objects.nonNull(getRequest().getParameter("message")) ?
                                    getRequest().getParameter("message") : "");
                    try {
                        EntityServiceFactory.getInstance().getOrderService().update(order);
                    } catch (ServiceException ex) {
                        StaticDataHandler.INSTANCE.getLOGGER().error("Order hasn't been rated cause of " + ex);
                    }
                });
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() +
                        (Objects.nonNull(getRequest().getParameter("backToCurrent")) ?
                                "/order_info?key=" + getRequest().getParameter("key") : "/client_orders"));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
