package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdminActiveOrdersCommand extends Command {

    public AdminActiveOrdersCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
                getRequest().setAttribute("orders",
                        EntityServiceFactory.getInstance().getOrderService().getList()
                        .stream()
                        .filter(order-> !order.isTaken())
                        .collect(Collectors.toList()));
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/aactiveorders.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        executeGet();
    }

}