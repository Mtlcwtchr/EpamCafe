package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.AdminCommand;
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

public class SaveHallCommand extends AdminCommand {

    public SaveHallCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeValidated() throws ControllerException {
        try{
            Hall hall = new Hall();
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if (Objects.nonNull(getRequest().getParameter("hallId")) &&
                    !getRequest().getParameter("hallId").isEmpty() &&
                    !getRequest().getParameter("hallId").isBlank()) {
                hall.setId(Integer.parseInt(getRequest().getParameter("hallId")));
            }
            if (Objects.nonNull(getRequest().getParameter("hallGuestsNumber")) &&
                    !getRequest().getParameter("hallGuestsNumber").isEmpty() &&
                    !getRequest().getParameter("hallGuestsNumber").isBlank()) {
                hall.setGuestsNumber(Integer.parseInt(getRequest().getParameter("hallGuestsNumber")));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            if (Objects.nonNull(getRequest().getParameter("hallName")) &&
                    !getRequest().getParameter("hallName").isEmpty() &&
                    !getRequest().getParameter("hallName").isBlank()) {
                hall.setName(getRequest().getParameter("hallName"));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            if (Objects.nonNull(getRequest().getParameter("hallDescription")) &&
                    !getRequest().getParameter("hallDescription").isEmpty() &&
                    !getRequest().getParameter("hallDescription").isBlank()) {
                hall.setDescription(getRequest().getParameter("hallDescription"));
            } else {
                WrongInteractionProcessor.wrongInteractionProcess(getRequest(), getResponse());
                return;
            }
            EntityServiceFactory.getInstance().getHallService().save(hall);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/admin_halls");
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
