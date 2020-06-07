package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Category;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class DeleteCategoryCommand extends Command {

    public DeleteCategoryCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            if (Objects.nonNull(getRequest().getParameter("chosenCategoryId")) &&
                    !getRequest().getParameter("chosenCategoryId").isBlank() &&
                    !getRequest().getParameter("chosenCategoryId").isEmpty()) {
                EntityServiceFactory.getInstance().getMealCategoryService().delete(Integer.parseInt(getRequest().getParameter("chosenCategoryId")));
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/categories");
        } catch ( ServiceException | IOException ex) {
            try {
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/something_went_wrong");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
