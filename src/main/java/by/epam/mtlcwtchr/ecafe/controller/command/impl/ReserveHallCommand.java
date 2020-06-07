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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            if (Objects.nonNull(getRequest().getParameter("chosenHallId")) &&
                    !getRequest().getParameter("chosenHallId").isEmpty() &&
                    !getRequest().getParameter("chosenHallId").isBlank()) {
                final Optional<Hall> hall = EntityServiceFactory.getInstance().getHallService().find(getRequest().getParameter("chosenHallId"));
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
                            proceedError("This date already been reserved, please choose another one", hall.get().getId());
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
                        proceedError("Please, choose time between " +
                                new SimpleDateFormat("HH:mm").format(ReservationConfig.INSTANCE.getCafeWorkDayBegin()) +
                                " and " +
                                new SimpleDateFormat("HH:mm").format(ReservationConfig.INSTANCE.getCafeWorkDayEnd()), hall.get().getId());
                        return;
                    }
                    if (Objects.nonNull(getRequest().getParameter("contactPhone"))) {
                        reservation.setContactPhone(getRequest().getParameter("contactPhone"));
                    }
                    EntityServiceFactory.getInstance().getReservationService().save(reservation);
                }
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/home");
        } catch (IOException | ServiceException | ParseException ex) {
            throw new ControllerException(ex);
        }
    }

    private void proceedError(String errorMsg, int hallId) throws IOException {
        ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/reservation?chosenHallId=" + hallId + "&error=" + errorMsg);
    }

}
