package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Category;
import by.epam.mtlcwtchr.ecafe.entity.Hall;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class SaveHallCommand extends Command {

    public SaveHallCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            Hall hall = new Hall();
            getRequest().setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            if (Objects.nonNull(getRequest().getParameter("hallNumber")) &&
                    !getRequest().getParameter("hallNumber").isEmpty() &&
                    !getRequest().getParameter("hallNumber").isBlank()) {
                hall.setId(Integer.parseInt(getRequest().getParameter("hallNumber")));
            }
            if (Objects.nonNull(getRequest().getParameter("hallGuestsNumber")) &&
                    !getRequest().getParameter("hallGuestsNumber").isEmpty() &&
                    !getRequest().getParameter("hallGuestsNumber").isBlank()) {
                hall.setGuestsNumber(Integer.parseInt(getRequest().getParameter("hallGuestsNumber")));
            }
            if (Objects.nonNull(getRequest().getParameter("hallName")) &&
                    !getRequest().getParameter("hallName").isEmpty() &&
                    !getRequest().getParameter("hallName").isBlank()) {
                hall.setHallName(getRequest().getParameter("hallName"));
            }
            if (Objects.nonNull(getRequest().getParameter("hallDescription")) &&
                    !getRequest().getParameter("hallDescription").isEmpty() &&
                    !getRequest().getParameter("hallDescription").isBlank()) {
                hall.setHallDescription(getRequest().getParameter("hallDescription"));
            }
            EntityServiceFactory.getInstance().getHallService().save(hall);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath() + "/halls");
        } catch (IOException | ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

}
