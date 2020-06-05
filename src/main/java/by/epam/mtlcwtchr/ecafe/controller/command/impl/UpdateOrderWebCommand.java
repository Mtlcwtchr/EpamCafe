package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Category;
import by.epam.mtlcwtchr.ecafe.entity.Order;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.command.CommandType;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class UpdateOrderWebCommand extends WebCommand {

    public UpdateOrderWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            final Command getCommand = Command.of(CommandType.GET_ORDER_COMMAND);
            getCommand.initParams(Integer.parseInt(getRequest().getParameter("chosenOrderId")));
            getCommand.execute();
            if (getCommand.getCommandResult().first()) {
                final Order order = (Order) getCommand.getCommandResult().get();
                final String[] params = getRequest().getParameterValues("params");
                order.setPaid(Arrays.toString(params).contains("isPaid"));
                order.setPrepared(Arrays.toString(params).contains("isPrepared"));
                order.setTaken(Arrays.toString(params).contains("isTaken"));
                final Command updateCommand = Command.of(CommandType.UPDATE_ORDER_COMMAND);
                updateCommand.initParams(order);
                updateCommand.execute();
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/aorders");
        } catch ( ServiceException | IOException ex) {
            executeGet();
        }
    }

}
