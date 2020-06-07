package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class AddMealToOrderCommand extends Command {

    public AddMealToOrderCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try {
            final Optional<Meal> meal = EntityServiceFactory.getInstance().getMealService().find(Integer.parseInt(getRequest().getParameter("chosenMealId")));
            if (meal.isPresent()) {
            final Client actor = (Client) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor");
            actor.getCurrentOrder().addMeal(meal.get());
            ((HttpServletRequest) getRequest()).getSession().removeAttribute("actor");
            ((HttpServletRequest) getRequest()).getSession().setAttribute("actor", actor);
        }
        ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath()+"/meals" + (Objects.isNull(getRequest().getParameter("category")) ||
                getRequest().getParameter("category").isEmpty() || getRequest().getParameter("category").isBlank() ? "" :
                "?category=" + getRequest().getParameter("category").toLowerCase()));
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
