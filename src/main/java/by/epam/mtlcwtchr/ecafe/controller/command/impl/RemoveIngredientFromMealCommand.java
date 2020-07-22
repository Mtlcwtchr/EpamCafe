package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.config.StaticDataHandler;
import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.AdminCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class RemoveIngredientFromMealCommand extends AdminCommand {

    public RemoveIngredientFromMealCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try {
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if (Objects.nonNull(getRequest().getParameter("ukey")) &&
                    !getRequest().getParameter("ukey").isBlank() &&
                    !getRequest().getParameter("ukey").isEmpty() &&
                    getRequest().getParameter("ukey").matches("[0-9]++")) {
                EntityServiceFactory.getInstance().getMealService().find(Integer.parseInt(getRequest().getParameter("ukey"))).ifPresent( meal -> {
                    try {
                        meal.removeIngredient(Integer.parseInt(getRequest().getParameter("rkey")));
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
