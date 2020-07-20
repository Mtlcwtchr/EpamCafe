package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
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

public class SaveIngredientCommand extends Command {

    public SaveIngredientCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            Ingredient ingredient = new Ingredient();
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if (Objects.nonNull(getRequest().getParameter("ingredientName")) &&
                    !getRequest().getParameter("ingredientName").isEmpty() &&
                    !getRequest().getParameter("ingredientName").isBlank()) {
                ingredient.setName(getRequest().getParameter("ingredientName"));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
            if (Objects.nonNull(getRequest().getParameter("ingredientPicUrl")) &&
                    !getRequest().getParameter("ingredientPicUrl").isEmpty() &&
                    !getRequest().getParameter("ingredientPicUrl").isBlank()) {
                ingredient.setPictureUrl(getRequest().getParameter("ingredientPicUrl"));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
            EntityServiceFactory.getInstance().getMealIngredientService().save(ingredient);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/aingredients");
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
