package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.config.MenuConfig;
import by.epam.mtlcwtchr.ecafe.config.StaticDataHandler;
import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Actor;
import by.epam.mtlcwtchr.ecafe.entity.Category;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DeleteCategoryCommand extends Command {

    public DeleteCategoryCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            if (Objects.nonNull(((HttpServletRequest) getRequest()).getSession().getAttribute("actor")) &&
                    ((Actor) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).isPromoted()) {
                executePost();
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException ex){
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {
        try{
            if (Objects.nonNull(getRequest().getParameter("dkey")) &&
                    !getRequest().getParameter("dkey").isBlank() &&
                    !getRequest().getParameter("dkey").isEmpty() &&
                    getRequest().getParameter("dkey").matches("[0-9]++")) {
                EntityServiceFactory.getInstance().getMealCategoryService().find(Integer.parseInt(getRequest().getParameter("dkey"))).ifPresent( category -> {
                    try {
                        EntityServiceFactory.getInstance().getMealCategoryService().find(MenuConfig.INSTANCE.getUnsetCategoryId()).ifPresent( unsetCategory -> {
                            try {
                                EntityServiceFactory.getInstance().getMealService().getList(category.getId()).forEach(meal -> {
                                    meal.setCategory(unsetCategory);
                                    try {
                                        EntityServiceFactory.getInstance().getMealService().update(meal);
                                    } catch (ServiceException ex) {
                                        StaticDataHandler.INSTANCE.getLOGGER().error("Meal " + meal + " category hasn't been set to " + unsetCategory + " cause of " + ex);
                                    }
                                });
                            } catch (ServiceException ex){
                                StaticDataHandler.INSTANCE.getLOGGER().error("Meals category hasn't been changed to " + unsetCategory + " cause of " + ex);
                            }
                        });
                    } catch (ServiceException ex) {
                        StaticDataHandler.INSTANCE.getLOGGER().error("Meals category hasn't been changed to defaults cause of " + ex);
                    }
                });
                if (EntityServiceFactory.getInstance().getMealCategoryService().delete(Integer.parseInt(getRequest().getParameter("dkey")))) {
                    ((List<Category>) ((HttpServletRequest) getRequest()).getSession().getAttribute("category"))
                            .removeIf(_c -> _c.getId()==Integer.parseInt(getRequest().getParameter("dkey")));
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
