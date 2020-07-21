package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.config.StaticDataHandler;
import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class AdminReservationCommand extends Command {

    private final int daysToLong = 86_400_000;

    public AdminReservationCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if (Objects.nonNull(getRequest().getParameter("hkey")) &&
                    !getRequest().getParameter("hkey").isEmpty() &&
                    !getRequest().getParameter("hkey").isBlank()) {
                if(getRequest().getParameter("hkey").matches("[0-9]++")) {
                    EntityServiceFactory.getInstance().getHallService().find(Integer.parseInt(getRequest().getParameter("hkey"))).ifPresent(hall->{
                        ((HttpServletRequest) getRequest()).getSession().setAttribute("hall", hall);
                        try {
                            ((HttpServletRequest) getRequest()).getSession().setAttribute("reservations",EntityServiceFactory.getInstance().getReservationService().getList(Integer.parseInt(getRequest().getParameter("hkey"))));
                        } catch (ServiceException ex) {
                            StaticDataHandler.INSTANCE.getLOGGER().error("Reservations of hall " + hall + " hasn't been got cause of " + ex);
                        }
                    });
                } else if(getRequest().getParameter("hkey").equals("all")) {
                    ((HttpServletRequest) getRequest()).getSession().setAttribute("reservations",EntityServiceFactory.getInstance().getReservationService().getList());
                }
            }
            getRequest().getRequestDispatcher("/WEB-INF/jsp/admin/adminreservation.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {

    }

}
