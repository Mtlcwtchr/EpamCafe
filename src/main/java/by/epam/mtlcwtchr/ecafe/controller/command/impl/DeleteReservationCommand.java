package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.AdminCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Reservation;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DeleteReservationCommand extends AdminCommand {

    public DeleteReservationCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            System.out.println(((HttpServletRequest) getRequest()).getSession().getAttribute("reservations"));
            if (Objects.nonNull(getRequest().getParameter("dkey")) &&
                    !getRequest().getParameter("dkey").isBlank() &&
                    !getRequest().getParameter("dkey").isEmpty() &&
                    getRequest().getParameter("dkey").matches("[0-9]++")) {
                if (EntityServiceFactory.getInstance().getReservationService().delete(Integer.parseInt(getRequest().getParameter("dkey")))) {
                    ((List<Reservation>) ((HttpServletRequest) getRequest()).getSession().getAttribute("reservation"))
                            .removeIf(_r -> _r.getId()==Integer.parseInt(getRequest().getParameter("dkey")));
                }
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_reservation?hkey=" + getRequest().getParameter("hkey"));
            } else  {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
            }
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
