package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Category;
import by.epam.mtlcwtchr.ecafe.entity.Ingredient;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
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
import java.util.Optional;

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
                if(Objects.nonNull(getRequest().getParameter("category"))) {
                    final Command getCat = Command.of(CommandType.GET_CATEGORY_COMMAND);
                    getCat.initParams(getRequest().getParameter("category"));
                    getCat.execute();
                    if(getCat.getCommandResult().first()) {
                        meal.setCategory((Category) getCat.getCommandResult().get());
                    }
                }
                if(Objects.nonNull(getRequest().getParameterValues("ingredient"))) {
                    System.out.println(Arrays.toString(getRequest().getParameterValues("ingredient")));
                    for (String ingredient : getRequest().getParameterValues("ingredient")) {
                        final Optional<Ingredient> ingr = meal.getIngredients().stream().filter(i -> i.getName().equals(ingredient)).findAny();
                        if(ingr.isPresent()) {
                            if(Objects.nonNull(getRequest().getParameter(ingredient+"NewMass")) &&
                                    !getRequest().getParameter(ingredient+"NewMass").isBlank() &&
                                    !getRequest().getParameter(ingredient+"NewMass").isEmpty()){
                                ingr.get().setMass(Integer.parseInt(getRequest().getParameter(ingredient + "NewMass")));
                            }
                        } else {
                            final Command getIngredient = Command.of(CommandType.GET_INGREDIENT_COMMAND);
                            getIngredient.initParams(ingredient);
                            getIngredient.execute();
                            if (getIngredient.getCommandResult().first() &&
                                    Objects.nonNull(getRequest().getParameter(ingredient+"NewMass")) &&
                                    !getRequest().getParameter(ingredient+"NewMass").isBlank() &&
                                    !getRequest().getParameter(ingredient+"NewMass").isEmpty() &&
                                    Integer.parseInt(getRequest().getParameter(ingredient+"NewMass"))!=0) {
                                final Ingredient gotIngredient = (Ingredient) getIngredient.getCommandResult().get();
                                gotIngredient.setMass(Integer.parseInt(getRequest().getParameter(ingredient+"NewMass")));
                                meal.addIngredient(gotIngredient);
                            }
                        }
                    }
                }
                final Command updateCommand = Command.of(CommandType.UPDATE_MEAL_COMMAND);
                updateCommand.initParams(meal);
                updateCommand.execute();
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/meals?open=" + getRequest().getParameter("chosenMealId"));
        } catch ( ServiceException | IOException ex) {
            executeGet();
        }
    }

}
