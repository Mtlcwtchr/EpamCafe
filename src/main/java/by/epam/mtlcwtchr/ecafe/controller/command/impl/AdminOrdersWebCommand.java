package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Order;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.command.CommandType;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdminOrdersWebCommand extends WebCommand {

    public AdminOrdersWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            final Command command = Command.of(CommandType.GET_ORDERS_COMMAND);
            command.initParams();
            command.execute();
            if(Objects.isNull(getRequest().getParameter("clientId")) ||
                     getRequest().getParameter("clientId").isBlank() ||
                     getRequest().getParameter("clientId").isEmpty()){
                getRequest().setAttribute("orders", command.getCommandResult().getList());
            } else {
                getRequest().setAttribute("orders",
                        command.getCommandResult().getList()
                        .stream()
                        .filter(order->((Order)order).getCustomer().getId()==Integer.parseInt(getRequest().getParameter("clientId")))
                        .collect(Collectors.toList()));
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
