package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Category;
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
import java.util.Objects;
import java.util.Optional;

public class UpdateCategoryWebCommand extends WebCommand {

    public UpdateCategoryWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            final Optional<Category> category = EntityServiceFactory.getInstance().getMealCategoryService().find(Integer.parseInt(getRequest().getParameter("chosenCategoryId")));
            if (category.isPresent()) {
                if (Objects.nonNull(getRequest().getParameter("categoryName"))) {
                    category.get().setName(getRequest().getParameter("categoryName"));
                }
                if (Objects.nonNull(getRequest().getParameter("categoryPicUrl"))) {
                    category.get().setPictureUrl(getRequest().getParameter("categoryPicUrl"));
                }
                EntityServiceFactory.getInstance().getMealCategoryService().update(category.get());
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/categories?open=" + getRequest().getParameter("chosenCategoryId"));
        } catch ( ServiceException | IOException ex) {
            executeGet();
        }
    }

}
