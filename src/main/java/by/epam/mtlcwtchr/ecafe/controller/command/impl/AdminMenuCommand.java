package by.epam.mtlcwtchr.ecafe.controller.command.impl;

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

public class AdminMenuCommand extends AdminCommand {

    public AdminMenuCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try {
            if(Objects.nonNull(getRequest().getParameter("key")) &&
                    !getRequest().getParameter("key").isBlank() &&
                    !getRequest().getParameter("key").isEmpty()) {
                        ((HttpServletRequest) getRequest()).getSession().removeAttribute("meals");
                        ((HttpServletRequest) getRequest()).getSession().setAttribute("meals",
                        getRequest().getParameter("key").equals("all") ?
                        EntityServiceFactory.getInstance().getMealService().getList() :
                        EntityServiceFactory.getInstance().getMealService().getList(Integer.parseInt(getRequest().getParameter("key"))));
            }
            ((HttpServletRequest) getRequest()).getSession().setAttribute("categories",
                    EntityServiceFactory.getInstance().getMealCategoryService().getList());
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/adminmenu.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
