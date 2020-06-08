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
import java.util.Optional;

public class UpdateCategoryCommand extends Command {

    public UpdateCategoryCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
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
            throw new ControllerException(ex);
        }
    }

}