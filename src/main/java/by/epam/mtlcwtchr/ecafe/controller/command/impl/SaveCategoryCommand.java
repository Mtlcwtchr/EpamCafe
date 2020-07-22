package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.AdminCommand;
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

public class SaveCategoryCommand extends AdminCommand {

    public SaveCategoryCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            Category category = new Category();
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if (Objects.nonNull(getRequest().getParameter("categoryName")) &&
                    !getRequest().getParameter("categoryName").isEmpty() &&
                    !getRequest().getParameter("categoryName").isBlank()) {
                category.setName(getRequest().getParameter("categoryName"));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            if (Objects.nonNull(getRequest().getParameter("categoryPictureUrl")) &&
                    !getRequest().getParameter("categoryPictureUrl").isEmpty() &&
                    !getRequest().getParameter("categoryPictureUrl").isBlank()) {
                category.setPictureUrl(getRequest().getParameter("categoryPictureUrl"));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            EntityServiceFactory.getInstance().getMealCategoryService().save(category);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_categories");
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
