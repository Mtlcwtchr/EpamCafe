package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Actor;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

public class MealsCommand extends Command {

    public MealsCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            if(Objects.isNull(getRequest().getParameter("category")) ||
                     getRequest().getParameter("category").isBlank() ||
                     getRequest().getParameter("category").isEmpty()) {
                getRequest().setAttribute("meals", EntityServiceFactory.getInstance().getMealService().getList());
            } else {
                getRequest().setAttribute("meals",
                        EntityServiceFactory.getInstance().getMealService().getList()
                        .stream()
                        .filter(meal-> meal.getCategory().getName().toLowerCase().equals(getRequest().getParameter("category").toLowerCase()))
                        .collect(Collectors.toList()));
            }
            getRequest().setAttribute("categories", EntityServiceFactory.getInstance().getMealCategoryService().getList());
            getRequest().setAttribute("ingredients", EntityServiceFactory.getInstance().getMealIngredientService().getList());
            getRequest().getRequestDispatcher(((Actor)((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).isPromoted()
                    ? "/WEB-INF/jsp/admin/ameals.jsp" : "/WEB-INF/jsp/meals.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {

    }

}
