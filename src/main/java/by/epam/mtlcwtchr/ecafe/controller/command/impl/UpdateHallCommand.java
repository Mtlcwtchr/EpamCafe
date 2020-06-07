package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Hall;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

public class UpdateHallCommand extends Command {

    public UpdateHallCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if(Objects.nonNull(getRequest().getParameter("chosenHallId")) &&
                    !getRequest().getParameter("chosenHallId").isEmpty() &&
                    !getRequest().getParameter("chosenHallId").isBlank()) {
                final Optional<Hall> hall = EntityServiceFactory.getInstance().getHallService().find(getRequest().getParameter("chosenHallId"));
                if (hall.isPresent()) {
                    if (Objects.nonNull(getRequest().getParameter("hallNumber")) &&
                            !getRequest().getParameter("hallNumber").isEmpty() &&
                            !getRequest().getParameter("hallNumber").isBlank()) {
                        hall.get().setId(Integer.parseInt(getRequest().getParameter("hallNumber")));
                    }
                    if (Objects.nonNull(getRequest().getParameter("hallGuestsNumber")) &&
                            !getRequest().getParameter("hallGuestsNumber").isEmpty() &&
                            !getRequest().getParameter("hallGuestsNumber").isBlank()) {
                        hall.get().setGuestsNumber(Integer.parseInt(getRequest().getParameter("hallGuestsNumber")));
                    }
                    if (Objects.nonNull(getRequest().getParameter("hallName")) &&
                            !getRequest().getParameter("hallName").isEmpty() &&
                            !getRequest().getParameter("hallName").isBlank()) {
                        hall.get().setHallName(getRequest().getParameter("hallName"));
                    }
                    if (Objects.nonNull(getRequest().getParameter("hallDescription")) &&
                            !getRequest().getParameter("hallDescription").isEmpty() &&
                            !getRequest().getParameter("hallDescription").isBlank()) {
                        hall.get().setHallDescription(getRequest().getParameter("hallDescription"));
                    }
                    EntityServiceFactory.getInstance().getHallService().update(hall.get());
                }
            }
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/halls");
        } catch (IOException | ServiceException ex) {
            try {
                ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/something_went_wrong");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
