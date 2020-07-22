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
import java.util.Optional;

public class UpdateCategoryCommand extends AdminCommand {

    public UpdateCategoryCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if(Objects.nonNull(getRequest().getParameter("key")) &&
                    !getRequest().getParameter("key").isEmpty() &&
                    !getRequest().getParameter("key").isBlank() &&
                    getRequest().getParameter("key").matches("[0-9]++")) {
                final Optional<Category> category = EntityServiceFactory.getInstance().getMealCategoryService().find(Integer.parseInt(getRequest().getParameter("key")));
                if (category.isPresent()) {
                    if (Objects.nonNull(getRequest().getParameter("categoryName"))) {
                        category.get().setName(getRequest().getParameter("categoryName"));
                    } else {
                        WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                    }
                    if (Objects.nonNull(getRequest().getParameter("categoryPictureUrl"))) {
                        category.get().setPictureUrl(getRequest().getParameter("categoryPictureUrl"));
                    } else {
                        WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                    }
                    EntityServiceFactory.getInstance().getMealCategoryService().update(category.get());
                }
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_categories");
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch ( ServiceException | IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
