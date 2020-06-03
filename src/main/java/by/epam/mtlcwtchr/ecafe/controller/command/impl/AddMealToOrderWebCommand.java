package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.bean.Client;
import by.epam.mtlcwtchr.ecafe.bean.Meal;
import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.command.CommandType;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class AddMealToOrderWebCommand extends WebCommand {

    public AddMealToOrderWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try {
        final Command command = Command.of(CommandType.GET_MEAL_COMMAND);
        command.initParams(Integer.parseInt(getRequest().getParameter("chosenMealId")));
        command.execute();
        if (command.getCommandResult().first()) {
            final Client actor = (Client) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor");
            actor.getCurrentOrder().addMeal((Meal)command.getCommandResult().get());
            ((HttpServletRequest) getRequest()).getSession().removeAttribute("actor");
            ((HttpServletRequest) getRequest()).getSession().setAttribute("actor", actor);
        }
        ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath()+"/meals");
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
