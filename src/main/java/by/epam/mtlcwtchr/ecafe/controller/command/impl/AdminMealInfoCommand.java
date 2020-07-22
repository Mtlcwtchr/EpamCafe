package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.AdminCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

public class AdminMealInfoCommand extends AdminCommand {

    public AdminMealInfoCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try {
            if(Objects.nonNull(getRequest().getParameter("key")) &&
                    !getRequest().getParameter("key").isBlank() &&
                    !getRequest().getParameter("key").isEmpty() &&
                    getRequest().getParameter("key").matches("[0-9]++")) {
                ((HttpServletRequest) getRequest()).getSession().removeAttribute("meal");
                ((HttpServletRequest) getRequest()).getSession().setAttribute("meal",
                        EntityServiceFactory.getInstance().getMealService().find(Integer.parseInt(getRequest().getParameter("key"))).orElseThrow());
                ((HttpServletRequest) getRequest()).getSession().setAttribute("categories",
                        EntityServiceFactory.getInstance().getMealCategoryService().getList());
                ((HttpServletRequest) getRequest()).getSession().setAttribute("ingredients",
                        EntityServiceFactory.getInstance().getMealIngredientService().getList());
                getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/adminmealinfo.jsp").forward(getRequest(), getResponse());
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
