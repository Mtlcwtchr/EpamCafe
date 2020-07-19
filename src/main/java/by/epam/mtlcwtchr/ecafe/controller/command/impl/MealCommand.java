package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class MealCommand extends Command {

    public MealCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            if(Objects.nonNull(getRequest().getParameter("key")) &&
                     !getRequest().getParameter("key").isBlank() &&
                     !getRequest().getParameter("key").isEmpty() &&
                      getRequest().getParameter("key").matches("[0-9]++")) {
                EntityServiceFactory
                        .getInstance()
                        .getMealService()
                        .find(Integer.parseInt(getRequest().getParameter("key")))
                        .ifPresent(this::resetMealAttribute);
            }
            getRequest().getRequestDispatcher("/WEB-INF/jsp/meal.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        executeGet();
    }

    private void resetMealAttribute(Meal newAttribute){
        ((HttpServletRequest) getRequest()).getSession().removeAttribute("meal");
        ((HttpServletRequest) getRequest()).getSession().setAttribute("meal", newAttribute);
    }

}
