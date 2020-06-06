package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Category;
import by.epam.mtlcwtchr.ecafe.entity.Ingredient;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.command.CommandType;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class SaveMealWebCommand extends WebCommand {

    public SaveMealWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            final Meal meal = new Meal();
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
                final Optional<Category> category = EntityServiceFactory.getInstance().getMealCategoryService().find(getRequest().getParameter("category"));
                category.ifPresent(meal::setCategory);
            }
            if(Objects.nonNull(getRequest().getParameterValues("ingredient"))) {
                for (String ingredient : getRequest().getParameterValues("ingredient")) {
                    final Optional<Ingredient> ingr = EntityServiceFactory.getInstance().getMealIngredientService().find(ingredient);
                    if (ingr.isPresent() &&
                            Objects.nonNull(getRequest().getParameter(ingredient+"NewMass")) &&
                            !getRequest().getParameter(ingredient+"NewMass").isBlank() &&
                            !getRequest().getParameter(ingredient+"NewMass").isEmpty() &&
                            Integer.parseInt(getRequest().getParameter(ingredient+"NewMass"))!=0) {
                        ingr.get().setMass(Integer.parseInt(getRequest().getParameter(ingredient+"NewMass")));
                        meal.addIngredient(ingr.get());
                    }
                }
            }
            EntityServiceFactory.getInstance().getMealService().update(meal);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/meals");
        } catch ( ServiceException | IOException ex) {
            executeGet();
        }
    }

}
