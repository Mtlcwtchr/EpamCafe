package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.config.ReservationConfig;
import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Actor;
import by.epam.mtlcwtchr.ecafe.entity.Reservation;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ReservationWebCommand extends WebCommand {

    public ReservationWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {
        try {
            final List<Reservation> reservations = EntityServiceFactory.getInstance().getReservationService().getList();
            for (Reservation reservation : reservations) {
                List<Date> suitableDatesList = new ArrayList<>();
                for(int i=0; i<ReservationConfig.INSTANCE.getMaxDaysForwardCanBeReserved(); ++i){
                    suitableDatesList.add(Date.from(Instant.from(LocalDate.now().plusDays(i))));
                }
                reservations.stream().filter(r->r.getTableNo()==reservation.getTableNo()).forEach(r->suitableDatesList.remove(r.getReservationDate()));

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


}
