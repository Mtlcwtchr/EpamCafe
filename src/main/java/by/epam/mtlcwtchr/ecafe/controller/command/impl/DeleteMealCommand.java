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

public class DeleteMealCommand extends Command {

    public DeleteMealCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            if (Objects.nonNull(getRequest().getParameter("chosenMealId")) &&
                    !getRequest().getParameter("chosenMealId").isBlank() &&
                    !getRequest().getParameter("chosenMealId").isEmpty()) {
                EntityServiceFactory.getInstance().getMealService().delete(Integer.parseInt(getRequest().getParameter("chosenMealId")));
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/meals");
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
