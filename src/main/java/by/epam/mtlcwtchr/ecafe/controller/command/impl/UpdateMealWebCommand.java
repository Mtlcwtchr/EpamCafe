package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Category;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.command.CommandType;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Objects;

public class UpdateMealWebCommand extends WebCommand {

    public UpdateMealWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            final Command getCommand = Command.of(CommandType.GET_MEAL_COMMAND);
            getCommand.initParams(Integer.parseInt(getRequest().getParameter("chosenMealId")));
            getCommand.execute();
            if (getCommand.getCommandResult().first()) {
                final Meal meal = (Meal) getCommand.getCommandResult().get();
                if (Objects.nonNull(getRequest().getParameter("mealName"))) {
                    meal.setName(getRequest().getParameter("mealName"));
                }
                if (Objects.nonNull(getRequest().getParameter("mealPicUrl"))) {
                    meal.setPictureUrl(getRequest().getParameter("mealPicUrl"));
                }
                if (Objects.nonNull(getRequest().getParameter("mealPrice"))) {
                    meal.setPrice(Integer.parseInt(getRequest().getParameter("mealPrice")));
                }
                if(Objects.nonNull(getRequest().getParameter("mealCategory"))) {
                    final Command getCat = Command.of(CommandType.GET_CATEGORY_COMMAND);
                    getCat.initParams(getRequest().getParameter("mealCategory"));
                    getCat.execute();
                    if(getCat.getCommandResult().first()) {
                        meal.setCategory((Category) getCat.getCommandResult().get());
                    }
                }
                final Command updateCommand = Command.of(CommandType.UPDATE_MEAL_COMMAND);
                updateCommand.initParams(meal);
                updateCommand.execute();
            }
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/ameals.jsp").forward(getRequest(), getResponse());
        } catch ( ServiceException | ServletException | IOException ex) {
            executeGet();
        }
    }

}
