package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Actor;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.command.CommandType;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CategoriesWebCommand extends WebCommand {

    public CategoriesWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            final Command command = Command.of(CommandType.GET_CATEGORIES_COMMAND);
            command.execute();
            getRequest().setAttribute("categories", command.getCommandResult().getList());
            getRequest().getRequestDispatcher(((Actor)((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).isPromoted()
                    ? "/WEB-INF/jsp/admin/acategories.jsp" : "/WEB-INF/jsp/categories.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {

    }

}
