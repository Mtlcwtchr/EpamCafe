package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class DeleteReservationCommand extends Command {

    public DeleteReservationCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            if (Objects.nonNull(getRequest().getParameter("chosenReservationId")) &&
                    !getRequest().getParameter("chosenReservationId").isBlank() &&
                    !getRequest().getParameter("chosenReservationId").isEmpty()) {
                EntityServiceFactory.getInstance().getReservationService().delete(Integer.parseInt(getRequest().getParameter("chosenReservationId")));
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/reservation?chosenHallId=" + getRequest().getParameter("chosenHallId"));
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
