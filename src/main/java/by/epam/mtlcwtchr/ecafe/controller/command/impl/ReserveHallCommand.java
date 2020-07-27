package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.config.ReservationConfig;
import by.epam.mtlcwtchr.ecafe.config.StaticDataHandler;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
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
import java.util.Date;
import java.util.Objects;

public class ReserveHallCommand extends Command {

    public ReserveHallCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void executePost() throws ControllerException {
        try {
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            final String key = getRequest().getParameter("key");
            if (Objects.nonNull(key) && !key.isEmpty() && !key.isBlank() && key.matches("\\d++")) {
                EntityServiceFactory.getInstance().getHallService().find(key).ifPresent(hall -> {
                    Reservation reservation = new Reservation();
                    reservation.setReservedHall(hall);
                    try {
                        setReservationDate(reservation);
                        setContactTime(reservation);
                        setContactPhone(reservation);
                        EntityServiceFactory.getInstance().getReservationService().save(reservation).ifPresent(res ->
                                ((HttpServletRequest) getRequest()).getSession().setAttribute("reservation", res));
                    } catch (ServiceException | ParseException | IOException ex) {
                        StaticDataHandler.INSTANCE.getLOGGER().error(ex);
                    }
                });
            }
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    private void setContactPhone(Reservation reservation) {
        final String contactPhone = getRequest().getParameter("contactPhone");
        if (Objects.nonNull(contactPhone) && !contactPhone.isEmpty() && !contactPhone.isBlank()) {
            reservation.setContactPhone(contactPhone);
        }
    }

    private void setContactTime(Reservation reservation) throws ParseException, IOException {
        final String contactTime = getRequest().getParameter("contactTime");
        final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        if(Objects.nonNull(contactTime) &&
                (timeFormat.parse(contactTime).before(ReservationConfig.INSTANCE.getCafeWorkDayEnd())) &&
                (timeFormat.parse(contactTime)).after(ReservationConfig.INSTANCE.getCafeWorkDayBegin())) {
            reservation.setContactTime(timeFormat.parse(contactTime));
        } else {
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/reservation?key=" + reservation.getReservedHall().getId() + "&status=timeError");
        }
    }

    private void setReservationDate(Reservation reservation) throws ParseException, ServiceException, IOException {
        if (Objects.nonNull(getRequest().getParameter("reservationDate"))) {
            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            final Date reservationDate = dateFormat.parse(getRequest().getParameter("reservationDate"));
            if (EntityServiceFactory.getInstance().getReservationService().getList()
                    .stream()
                    .anyMatch(res ->  res.getReservationDate().equals(reservationDate))) {
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/reservation?key=" + reservation.getReservedHall().getId() + "&status=dateError");
            } else {
                reservation.setReservationDate(reservationDate);
            }
        }
    }


}
