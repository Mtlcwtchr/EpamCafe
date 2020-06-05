package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Actor;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.command.CommandType;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import org.apache.catalina.core.StandardContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

public class MealsWebCommand extends WebCommand {

    public MealsWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            final Command command = Command.of(CommandType.GET_MEALS_COMMAND);
            command.execute();
            if(Objects.isNull(getRequest().getParameter("category")) ||
                     getRequest().getParameter("category").isBlank() ||
                     getRequest().getParameter("category").isEmpty()) {
                getRequest().setAttribute("meals", command.getCommandResult().getList());
            } else {
                getRequest().setAttribute("meals",
                        command.getCommandResult().getList()
                        .stream()
                        .filter(meal->((Meal)meal).getCategory().getName().toLowerCase().equals(getRequest().getParameter("category").toLowerCase()))
                        .collect(Collectors.toList()));
            }
            final Command getCategories = Command.of(CommandType.GET_CATEGORIES_COMMAND);
            getCategories.execute();
            getRequest().setAttribute("categories", getCategories.getCommandResult().getList());
            final Command getIngredients = Command.of(CommandType.GET_INGREDIENTS_COMMAND);
            getIngredients.execute();
            getRequest().setAttribute("ingredients", getIngredients.getCommandResult().getList());
            getRequest().getRequestDispatcher(((Actor)((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).isPromoted()
                    ? "/WEB-INF/jsp/admin/ameals.jsp" : "/WEB-INF/jsp/meals.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {

    }

}
