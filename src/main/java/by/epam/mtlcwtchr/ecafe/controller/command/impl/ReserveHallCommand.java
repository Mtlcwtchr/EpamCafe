package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.config.ReservationConfig;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Hall;
import by.epam.mtlcwtchr.ecafe.entity.Reservation;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Optional;

public class ReserveHallCommand extends Command {

    public ReserveHallCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try {
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if (Objects.nonNull(getRequest().getParameter("key")) &&
                    !getRequest().getParameter("key").isEmpty() &&
                    !getRequest().getParameter("key").isBlank() &&
                    getRequest().getParameter("key").matches("[0-9]++")) {
                final Optional<Hall> hall = EntityServiceFactory.getInstance().getHallService().find(getRequest().getParameter("key"));
                if (hall.isPresent()) {
                    Reservation reservation = new Reservation();
                    reservation.setReservedHall(hall.get());
                    if (Objects.nonNull(getRequest().getParameter("reservationDate"))) {
                        if (EntityServiceFactory.getInstance().getReservationService().getList()
                                .stream()
                                .anyMatch(res -> {
                                    try {
                                        return res.getReservationDate().equals(new SimpleDateFormat("yyyy-MM-dd").parse(getRequest().getParameter("reservationDate")));
                                    } catch (ParseException e) {
                                        return true;
                                    }
                                })) {
                            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/reservation?key=" + hall.get().getId() + "&status=dateError");
                            return;
                        } else {
                            reservation.setReservationDate(new SimpleDateFormat("yyyy-MM-dd").parse(getRequest().getParameter("reservationDate")));
                        }
                    }
                    if(Objects.nonNull(getRequest().getParameter("contactTime")) &&
                            (new SimpleDateFormat("HH:mm").parse(getRequest().getParameter("contactTime")).before(ReservationConfig.INSTANCE.getCafeWorkDayEnd())) &&
                            (new SimpleDateFormat("HH:mm").parse(getRequest().getParameter("contactTime"))).after(ReservationConfig.INSTANCE.getCafeWorkDayBegin())) {
                        reservation.setContactTime(new SimpleDateFormat("HH:mm").parse(getRequest().getParameter("contactTime")));
                    } else {
                        ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/reservation?key=" + hall.get().getId() + "&status=timeError");
                    }
                    if (Objects.nonNull(getRequest().getParameter("contactPhone"))) {
                        reservation.setContactPhone(getRequest().getParameter("contactPhone"));
                    }
                    EntityServiceFactory.getInstance().getReservationService().save(reservation).ifPresent(res -> ((HttpServletRequest) getRequest()).getSession().setAttribute("reservation", res));
                }
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/halls?status=success");
        } catch (IOException | ServiceException | ParseException ex) {
            throw new ControllerException(ex);
        }
    }


}
