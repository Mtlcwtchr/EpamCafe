package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.config.StaticDataHandler;
import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Actor;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class AddIngredientToMealCommand extends Command {

    public AddIngredientToMealCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            if (Objects.nonNull(((HttpServletRequest) getRequest()).getSession().getAttribute("actor")) &&
                    ((Actor) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).isPromoted()) {
                executePost();
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException ex){
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        try {
            System.out.println(getRequest().getParameter("ukey"));
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if (Objects.nonNull(getRequest().getParameter("ukey")) &&
                    !getRequest().getParameter("ukey").isBlank() &&
                    !getRequest().getParameter("ukey").isEmpty() &&
                    getRequest().getParameter("ukey").matches("[0-9]++")) {
                EntityServiceFactory.getInstance().getMealService().find(Integer.parseInt(getRequest().getParameter("ukey"))).ifPresent( meal -> {
                    try {
                        EntityServiceFactory.getInstance().getMealIngredientService().find(getRequest().getParameter("ingredientName")).ifPresent(ingredient -> {
                            ingredient.setMass(Integer.parseInt(getRequest().getParameter("ingredientMass")));
                            meal.addIngredient(ingredient);
                        });
                        EntityServiceFactory.getInstance().getMealService().update(meal);
                    } catch (ServiceException ex) {
                        StaticDataHandler.INSTANCE.getLOGGER().error("Meal " + meal + " hasn't been updated cause of " + ex);
                    }
                });
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_meal_info?key=" + getRequest().getParameter("ukey"));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
