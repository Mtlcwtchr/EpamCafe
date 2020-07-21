package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
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
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

public class SaveMealCommand extends Command {

    public SaveMealCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            final Meal meal = new Meal();
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if (Objects.nonNull(getRequest().getParameter("mealName"))) {
                meal.setName(getRequest().getParameter("mealName"));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            if (Objects.nonNull(getRequest().getParameter("mealPictureUrl"))) {
                meal.setPictureUrl(getRequest().getParameter("mealPictureUrl"));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            if (Objects.nonNull(getRequest().getParameter("mealPrice"))) {
                meal.setPrice(Integer.parseInt(getRequest().getParameter("mealPrice")));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            if(Objects.nonNull(getRequest().getParameter("mealCategoryName"))) {
                final Optional<Category> category = EntityServiceFactory.getInstance().getMealCategoryService().find(getRequest().getParameter("mealCategoryName"));
                category.ifPresent(meal::setCategory);
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            /*if(Objects.nonNull(getRequest().getParameterValues("ingredient"))) {
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
            }*/
            EntityServiceFactory.getInstance().getMealService().save(meal);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_menu?key=all");
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
