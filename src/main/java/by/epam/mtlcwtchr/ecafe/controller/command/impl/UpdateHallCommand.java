package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Hall;
import by.epam.mtlcwtchr.ecafe.entity.Ingredient;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
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
                if(Objects.nonNull(getRequest().getParameter("ukey")) &&
                            !getRequest().getParameter("ukey").isEmpty() &&
                            !getRequest().getParameter("ukey").isBlank() &&
                            getRequest().getParameter("ukey").matches("[0-9]++")) {
                final Optional<Hall> hall = ((List<Hall>)((HttpServletRequest) getRequest()).getSession().getAttribute("halls"))
                        .stream()
                        .filter(_h -> _h.getId() == Integer.parseInt(getRequest().getParameter("ukey")))
                        .findAny();
                if (hall.isPresent()) {
                    if (Objects.nonNull(getRequest().getParameter("hallId")) &&
                            !getRequest().getParameter("hallId").isEmpty() &&
                            !getRequest().getParameter("hallId").isBlank()) {
                        hall.get().setId(Integer.parseInt(getRequest().getParameter("hallId")));
                    } else {
                        WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                        return;
                    }
                    if (Objects.nonNull(getRequest().getParameter("hallGuestsNumber")) &&
                            !getRequest().getParameter("hallGuestsNumber").isEmpty() &&
                            !getRequest().getParameter("hallGuestsNumber").isBlank()) {
                        hall.get().setGuestsNumber(Integer.parseInt(getRequest().getParameter("hallGuestsNumber")));
                    } else {
                        WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                        return;
                    }
                    if (Objects.nonNull(getRequest().getParameter("hallName")) &&
                            !getRequest().getParameter("hallName").isEmpty() &&
                            !getRequest().getParameter("hallName").isBlank()) {
                        hall.get().setName(getRequest().getParameter("hallName"));
                    } else {
                        WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                        return;
                    }
                    if (Objects.nonNull(getRequest().getParameter("hallDescription")) &&
                            !getRequest().getParameter("hallDescription").isEmpty() &&
                            !getRequest().getParameter("hallDescription").isBlank()) {
                        hall.get().setDescription(getRequest().getParameter("hallDescription"));
                    } else {
                        WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                        return;
                    }
                    EntityServiceFactory.getInstance().getHallService().update(hall.get());
                }
                    ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_halls");
                } else {
                    WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                }
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
