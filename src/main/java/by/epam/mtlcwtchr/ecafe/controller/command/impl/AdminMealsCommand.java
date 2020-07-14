package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

public class AdminMealsCommand extends Command {

    public AdminMealsCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            if(Objects.nonNull(getRequest().getParameter("categoryId")) &&
                    !getRequest().getParameter("categoryId").isBlank() &&
                    !getRequest().getParameter("categoryId").isEmpty()) {
                    ((HttpServletRequest) getRequest()).getSession().removeAttribute("meals");
                    ((HttpServletRequest) getRequest()).getSession().setAttribute("meals",
                        getRequest().getParameter("categoryId").equals("all") ?
                        EntityServiceFactory.getInstance().getMealService().getList() :
                        EntityServiceFactory.getInstance().getMealService().getList(Integer.parseInt(getRequest().getParameter("categoryId"))));
            }
            getRequest().setAttribute("categories", EntityServiceFactory.getInstance().getMealCategoryService().getList());
            getRequest().setAttribute("ingredients", EntityServiceFactory.getInstance().getMealIngredientService().getList());
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/ameals.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {

    }

}
