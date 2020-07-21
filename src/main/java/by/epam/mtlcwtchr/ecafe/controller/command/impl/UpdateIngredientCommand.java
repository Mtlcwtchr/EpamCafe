package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Ingredient;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
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

public class UpdateIngredientCommand extends Command {

    public UpdateIngredientCommand(ServletRequest request, ServletResponse response){
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
                Optional<Ingredient> ingredient = ((List<Ingredient>)((HttpServletRequest) getRequest()).getSession().getAttribute("ingredients"))
                        .stream()
                        .filter(_i -> _i.getId() == Integer.parseInt(getRequest().getParameter("ukey")))
                        .findAny();
                if(ingredient.isPresent()){
                    if (Objects.nonNull(getRequest().getParameter("ingredientName")) &&
                            !getRequest().getParameter("ingredientName").isEmpty() &&
                            !getRequest().getParameter("ingredientName").isBlank()) {
                        ingredient.get().setName(getRequest().getParameter("ingredientName"));
                    }
                    if (Objects.nonNull(getRequest().getParameter("ingredientPictureUrl")) &&
                            !getRequest().getParameter("ingredientPictureUrl").isEmpty() &&
                            !getRequest().getParameter("ingredientPictureUrl").isBlank()) {
                        ingredient.get().setPictureUrl(getRequest().getParameter("ingredientPictureUrl"));
                    }
                    EntityServiceFactory.getInstance().getMealIngredientService().update(ingredient.get());
                }
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_ingredients");
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
