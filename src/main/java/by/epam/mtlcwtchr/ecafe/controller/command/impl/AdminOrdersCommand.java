package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.AdminCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Order;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdminOrdersCommand extends AdminCommand {

    public AdminOrdersCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try {
            if(Objects.nonNull(getRequest().getParameter("key")) &&
                    !getRequest().getParameter("key").isBlank() &&
                    !getRequest().getParameter("key").isEmpty()) {
                ((HttpServletRequest) getRequest()).getSession().removeAttribute("orders");
                ((HttpServletRequest) getRequest()).getSession().setAttribute("orders",
                                getRequest().getParameter("key").equals("all") ?
                                    EntityServiceFactory.getInstance().getOrderService().getList() :
                                getRequest().getParameter("key").equals("active") ?
                                    EntityServiceFactory.getInstance().getOrderService().getList()
                                            .stream()
                                            .filter(Predicate.not(Order::isTaken))
                                            .collect(Collectors.toList()) :
                                    EntityServiceFactory.getInstance().getOrderService().getList(Integer.parseInt(getRequest().getParameter("key"))));
            }
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/adminorders.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
