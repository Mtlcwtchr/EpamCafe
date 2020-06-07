package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class DeleteHallCommand extends Command {

    public DeleteHallCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            if (Objects.nonNull(getRequest().getParameter("chosenHallId")) &&
                    !getRequest().getParameter("chosenHallId").isBlank() &&
                    !getRequest().getParameter("chosenHallId").isEmpty()) {
                EntityServiceFactory.getInstance().getHallService().delete(Integer.parseInt(getRequest().getParameter("chosenHallId")));
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/halls");
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
