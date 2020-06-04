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
import java.io.IOException;
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
                if (Objects.nonNull(getRequest().getParameter("isPaid"))) {
                    order.setPaid(Boolean.parseBoolean(getRequest().getParameter("isPaid")));
                }
                if (Objects.nonNull(getRequest().getParameter("isPrepared"))) {
                    order.setPrepared(Boolean.parseBoolean(getRequest().getParameter("isPrepared")));
                }
                if (Objects.nonNull(getRequest().getParameter("isTaken"))) {
                    order.setTaken(Boolean.parseBoolean(getRequest().getParameter("isTaken")));
                }
                final Command updateCommand = Command.of(CommandType.UPDATE_ORDER_COMMAND);
                updateCommand.initParams(order);
                updateCommand.execute();
            }
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/aorders.jsp").forward(getRequest(), getResponse());
        } catch ( ServiceException | ServletException | IOException ex) {
            executeGet();
        }
    }

}
