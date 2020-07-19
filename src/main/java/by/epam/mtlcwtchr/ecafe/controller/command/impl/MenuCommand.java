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

public class MenuCommand extends Command {

    public MenuCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            getRequest().setAttribute("categories", EntityServiceFactory.getInstance().getMealCategoryService().getList());
            if(Objects.nonNull(getRequest().getParameter("key")) &&
                     !getRequest().getParameter("key").isBlank() &&
                     !getRequest().getParameter("key").isEmpty() &&
                      getRequest().getParameter("key").matches("[0-9]++")) {
                     ((HttpServletRequest) getRequest()).getSession().removeAttribute("meals");
                     ((HttpServletRequest) getRequest()).getSession().setAttribute("meals",
                        EntityServiceFactory.getInstance().getMealService().getList(Integer.parseInt(getRequest().getParameter("key"))));
            }
            getRequest().getRequestDispatcher("/WEB-INF/jsp/menu.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {

    }

}
