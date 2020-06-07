package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class DeleteIngredientCommand extends Command {

    public DeleteIngredientCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            if (Objects.nonNull(getRequest().getParameter("chosenIngredientId")) &&
                    !getRequest().getParameter("chosenIngredientId").isBlank() &&
                    !getRequest().getParameter("chosenIngredientId").isEmpty()) {
                EntityServiceFactory.getInstance().getMealIngredientService().delete(Integer.parseInt(getRequest().getParameter("chosenIngredientId")));
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/aingredients");
        } catch ( ServiceException | IOException ex) {
            try {
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/something_went_wrong");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
