package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Category;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.entity.Order;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
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
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if(Objects.nonNull(getRequest().getParameter("ukey")) &&
                    !getRequest().getParameter("ukey").isBlank() &&
                    !getRequest().getParameter("ukey").isEmpty() &&
                    getRequest().getParameter("ukey").matches("[0-9]++")) {
                final Optional<Meal> meal = ((List<Meal>)((HttpServletRequest) getRequest()).getSession().getAttribute("meals"))
                        .stream()
                        .filter(_m -> _m.getId() == Integer.parseInt(getRequest().getParameter("ukey")))
                        .findAny();
                if (meal.isPresent()) {
                    if (Objects.nonNull(getRequest().getParameter("mealName"))) {
                        meal.get().setName(getRequest().getParameter("mealName"));
                    }
                    if (Objects.nonNull(getRequest().getParameter("mealPictureUrl"))) {
                        meal.get().setPictureUrl(getRequest().getParameter("mealPictureUrl"));
                    }
                    if (Objects.nonNull(getRequest().getParameter("mealPrice"))) {
                        meal.get().setPrice(Integer.parseInt(getRequest().getParameter("mealPrice")));
                    }
                    if (Objects.nonNull(getRequest().getParameter("mealCategoryName"))) {
                        final Optional<Category> category = EntityServiceFactory.getInstance().getMealCategoryService().find(getRequest().getParameter("mealCategoryName"));
                        category.ifPresent(value -> meal.get().setCategory(value));
                    }
                    EntityServiceFactory.getInstance().getMealService().update(meal.get());
                }
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() +
                        (Objects.nonNull(getRequest().getParameter("backToCurrent")) ?
                        "/admin_meal_info?key=" :
                        "/admin_menu?key=") + getRequest().getParameter("key"));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
