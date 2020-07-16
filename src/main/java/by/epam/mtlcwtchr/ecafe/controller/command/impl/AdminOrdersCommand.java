package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdminOrdersCommand extends Command {

    public AdminOrdersCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            if(Objects.nonNull(getRequest().getParameter("chosenClientId")) &&
                    !getRequest().getParameter("chosenClientId").isBlank() &&
                    !getRequest().getParameter("chosenClientId").isEmpty()) {
                ((HttpServletRequest) getRequest()).getSession().removeAttribute("orders");
                ((HttpServletRequest) getRequest()).getSession().setAttribute("orders",
                        getRequest().getParameter("chosenClientId").equals("all") ?
                                EntityServiceFactory.getInstance().getOrderService().getList() :
                                EntityServiceFactory.getInstance().getOrderService().getList(Integer.parseInt(getRequest().getParameter("chosenClientId"))));
            }
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/aorders.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        executeGet();
    }

}
