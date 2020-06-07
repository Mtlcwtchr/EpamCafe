package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.config.ReservationConfig;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Actor;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.entity.Hall;
import by.epam.mtlcwtchr.ecafe.entity.Reservation;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReservationCommand extends Command {

    private final int daysToLong = 86_400_000;

    public ReservationCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            if ((((Actor)((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).isPromoted())) {
                fillAttributesForAdmin();
            } else {
                fillAttributesForClient();
            }
            getRequest().getRequestDispatcher(((Actor)((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).isPromoted()
                    ? "/WEB-INF/jsp/admin/areservation.jsp" : "/WEB-INF/jsp/reservation.jsp").forward(getRequest(), getResponse());
        } catch (ServletException | IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Override
    public void executePost() throws ControllerException {

    }

    private void fillAttributesForAdmin() throws ServiceException {
        if (Objects.nonNull(getRequest().getParameter("chosenHallId")) &&
                !getRequest().getParameter("chosenHallId").isEmpty() &&
                !getRequest().getParameter("chosenHallId").isBlank()) {
            final Optional<Hall> hall = EntityServiceFactory.getInstance().getHallService().find(Integer.parseInt(getRequest().getParameter("chosenHallId")));
            if (hall.isPresent()) {
                getRequest().setAttribute("hall", hall.get());
                getRequest().setAttribute("reservations",EntityServiceFactory.getInstance().getReservationService().getList().stream().filter(reservation -> reservation.getReservedHall().equals(hall.get())).collect(Collectors.toList()));
            }
        }
    }

    private void fillAttributesForClient() throws ServiceException {
        getRequest().setAttribute("error", getRequest().getParameter("error"));
        if (Objects.nonNull(getRequest().getParameter("chosenHallId")) &&
                !getRequest().getParameter("chosenHallId").isEmpty() &&
                !getRequest().getParameter("chosenHallId").isBlank()) {
            final Optional<Hall> hall = EntityServiceFactory.getInstance().getHallService().find(Integer.parseInt(getRequest().getParameter("chosenHallId")));
            if (hall.isPresent()) {
                getRequest().setAttribute("hall", hall.get());
                EntityServiceFactory.getInstance().getReservationService().getList()
                        .stream()
                        .filter(reservation -> reservation.getReservedHall().equals(hall.get()))
                        .forEach(reservation -> getRequest().setAttribute("reservedDates",reservation.getReservationDate()));
                getRequest().setAttribute("minDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                getRequest().setAttribute("maxDate",
                        new SimpleDateFormat("yyyy-MM-dd").format(new Date(new Date().getTime() + ((long)ReservationConfig.INSTANCE.getMaxDaysForwardCanBeReserved() * (long)daysToLong))));
                getRequest().setAttribute("minTime", new SimpleDateFormat("HH:mm").format(ReservationConfig.INSTANCE.getCafeWorkDayBegin()));
                getRequest().setAttribute("maxTime", new SimpleDateFormat("HH:mm").format(ReservationConfig.INSTANCE.getCafeWorkDayEnd()));
                getRequest().setAttribute("phone", Objects.isNull(((Client) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).getUser().getPhone())
                        ? "8029" : ((Client) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor")).getUser().getPhone());
            }
        }
    }

}
