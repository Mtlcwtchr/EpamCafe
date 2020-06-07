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
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class SaveCategoryCommand extends Command {

    public SaveCategoryCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            Category category = new Category();
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if (Objects.nonNull(getRequest().getParameter("categoryName")) &&
                    !getRequest().getParameter("categoryName").isEmpty() &&
                    !getRequest().getParameter("categoryName").isBlank()) {
                category.setName(getRequest().getParameter("categoryName"));
            } else {
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/something_went_wrong");
            }
            if (Objects.nonNull(getRequest().getParameter("categoryPicUrl")) &&
                    !getRequest().getParameter("categoryPicUrl").isEmpty() &&
                    !getRequest().getParameter("categoryPicUrl").isBlank()) {
                category.setPictureUrl(getRequest().getParameter("categoryPicUrl"));
            } else {
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/something_went_wrong");
            }
            EntityServiceFactory.getInstance().getMealCategoryService().save(category);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/categories");
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
