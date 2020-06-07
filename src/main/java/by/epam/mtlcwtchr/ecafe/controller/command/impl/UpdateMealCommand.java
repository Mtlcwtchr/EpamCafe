package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Category;
import by.epam.mtlcwtchr.ecafe.entity.Ingredient;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class UpdateMealCommand extends Command {

    public UpdateMealCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            final Optional<Meal> meal = EntityServiceFactory.getInstance().getMealService().find(Integer.parseInt(getRequest().getParameter("chosenMealId")));
            if (meal.isPresent()) {
                if (Objects.nonNull(getRequest().getParameter("mealName"))) {
                    meal.get().setName(getRequest().getParameter("mealName"));
                }
                if (Objects.nonNull(getRequest().getParameter("mealPicUrl"))) {
                    meal.get().setPictureUrl(getRequest().getParameter("mealPicUrl"));
                }
                if (Objects.nonNull(getRequest().getParameter("mealPrice"))) {
                    meal.get().setPrice(Integer.parseInt(getRequest().getParameter("mealPrice")));
                }
                if(Objects.nonNull(getRequest().getParameter("category"))) {
                    final Optional<Category> category = EntityServiceFactory.getInstance().getMealCategoryService().find(getRequest().getParameter("category"));
                    category.ifPresent(value -> meal.get().setCategory(value));
                }
                if(Objects.nonNull(getRequest().getParameterValues("ingredient"))) {
                    for (String ingredient : getRequest().getParameterValues("ingredient")) {
                        final Optional<Ingredient> ingr = meal.get().getIngredients().stream().filter(i -> i.getName().equals(ingredient)).findAny();
                        if(ingr.isPresent()) {
                            if(Objects.nonNull(getRequest().getParameter(ingredient+"NewMass")) &&
                                    !getRequest().getParameter(ingredient+"NewMass").isBlank() &&
                                    !getRequest().getParameter(ingredient+"NewMass").isEmpty()){
                                ingr.get().setMass(Integer.parseInt(getRequest().getParameter(ingredient + "NewMass")));
                            }
                        } else {
                            final Optional<Ingredient> ingred = EntityServiceFactory.getInstance().getMealIngredientService().find(ingredient);
                            if (ingred.isPresent() &&
                                    Objects.nonNull(getRequest().getParameter(ingredient+"NewMass")) &&
                                    !getRequest().getParameter(ingredient+"NewMass").isBlank() &&
                                    !getRequest().getParameter(ingredient+"NewMass").isEmpty() &&
                                    Integer.parseInt(getRequest().getParameter(ingredient+"NewMass"))!=0) {
                                ingred.get().setMass(Integer.parseInt(getRequest().getParameter(ingredient+"NewMass")));
                                meal.get().addIngredient(ingred.get());
                            }
                        }
                    }
                }
                EntityServiceFactory.getInstance().getMealService().update(meal.get());
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/meals?open=" + getRequest().getParameter("chosenMealId"));
        } catch ( ServiceException | IOException ex) {
            executeGet();
        }
    }

}
