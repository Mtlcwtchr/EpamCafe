package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.config.ReservationConfig;
import by.epam.mtlcwtchr.ecafe.config.StaticDataHandler;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Actor;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.entity.Hall;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ReservationCommand extends Command {

    private final int daysToLong = 86_400_000;

    public ReservationCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if ((((Actor)((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).isPromoted())) {
                fillAttributesForAdmin();
            } else {
                fillAttributesForClient();
            }
            getRequest().getRequestDispatcher(((Actor)((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).isPromoted()
                    ? "/WEB-INF/jsp/admin/adminreservation.jsp" : "/WEB-INF/jsp/reservation.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {

    }

    private void fillAttributesForAdmin() throws ServiceException {
        if (Objects.nonNull(getRequest().getParameter("key")) &&
                !getRequest().getParameter("key").isEmpty() &&
                !getRequest().getParameter("key").isBlank() &&
                getRequest().getParameter("key").matches("[0-9]++")) {
                EntityServiceFactory.getInstance().getHallService().find(Integer.parseInt(getRequest().getParameter("key"))).ifPresent(hall->{
                ((HttpServletRequest) getRequest()).getSession().setAttribute("hall", hall);
                try {
                    ((HttpServletRequest) getRequest()).getSession().setAttribute("reservations",EntityServiceFactory.getInstance().getReservationService().getList(Integer.parseInt(getRequest().getParameter("key"))));
                } catch (ServiceException ex) {
                    StaticDataHandler.INSTANCE.getLOGGER().error("Reservations of hall " + hall + " hasn't been got cause of " + ex);
                }
            });
        }
    }

    private void fillAttributesForClient() throws ServiceException {
        getRequest().setAttribute("error", getRequest().getParameter("error"));
        if (Objects.nonNull(getRequest().getParameter("key")) &&
                !getRequest().getParameter("key").isEmpty() &&
                !getRequest().getParameter("key").isBlank() &&
                getRequest().getParameter("key").matches("[0-9]++")) {
            final HttpSession session = ((HttpServletRequest) getRequest()).getSession();
            EntityServiceFactory.getInstance().getHallService().find(Integer.parseInt(getRequest().getParameter("key"))).ifPresent( hall -> {
                session.setAttribute("hall", hall);
                session.setAttribute("minDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                session.setAttribute("maxDate",
                        new SimpleDateFormat("yyyy-MM-dd").format(new Date(new Date().getTime() + ((long)ReservationConfig.INSTANCE.getMaxDaysForwardCanBeReserved() * (long)daysToLong))));
                session.setAttribute("minTime", new SimpleDateFormat("HH:mm").format(ReservationConfig.INSTANCE.getCafeWorkDayBegin()));
                session.setAttribute("maxTime", new SimpleDateFormat("HH:mm").format(ReservationConfig.INSTANCE.getCafeWorkDayEnd()));
            });
        }
    }

}
