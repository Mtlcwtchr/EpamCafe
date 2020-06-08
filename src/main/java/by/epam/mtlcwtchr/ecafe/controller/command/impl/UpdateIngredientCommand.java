package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Ingredient;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
            if(Objects.nonNull(getRequest().getParameter("chosenIngredientId")) &&
                    !getRequest().getParameter("chosenIngredientId").isBlank() &&
                    !getRequest().getParameter("chosenIngredientId").isEmpty()) {
                Optional<Ingredient> ingredient = EntityServiceFactory.getInstance().getMealIngredientService().find(Integer.parseInt(getRequest().getParameter("chosenIngredientId")));
                if(ingredient.isPresent()){
                    if (Objects.nonNull(getRequest().getParameter("ingredientName")) &&
                            !getRequest().getParameter("ingredientName").isEmpty() &&
                            !getRequest().getParameter("ingredientName").isBlank()) {
                        ingredient.get().setName(getRequest().getParameter("ingredientName"));
                    }
                    if (Objects.nonNull(getRequest().getParameter("ingredientPicUrl")) &&
                            !getRequest().getParameter("ingredientPicUrl").isEmpty() &&
                            !getRequest().getParameter("ingredientPicUrl").isBlank()) {
                        ingredient.get().setPictureUrl(getRequest().getParameter("ingredientPicUrl"));
                    }
                    EntityServiceFactory.getInstance().getMealIngredientService().update(ingredient.get());
                }
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/aingredients");
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
