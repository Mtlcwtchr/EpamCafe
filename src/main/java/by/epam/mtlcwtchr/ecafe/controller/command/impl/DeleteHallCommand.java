package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.controller.WrongInteractionProcessor;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.entity.Client;
import by.epam.mtlcwtchr.ecafe.entity.Hall;
import by.epam.mtlcwtchr.ecafe.entity.Meal;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;
import by.epam.mtlcwtchr.ecafe.service.factory.impl.EntityServiceFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DeleteHallCommand extends Command {

    public DeleteHallCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try{
            if (Objects.nonNull(getRequest().getParameter("dkey")) &&
                    !getRequest().getParameter("dkey").isBlank() &&
                    !getRequest().getParameter("dkey").isEmpty() &&
                    getRequest().getParameter("dkey").matches("[0-9]++")) {
                if (EntityServiceFactory.getInstance().getHallService().delete(Integer.parseInt(getRequest().getParameter("dkey")))) {
                    ((List<Hall>) ((HttpServletRequest) getRequest()).getSession().getAttribute("halls"))
                            .removeIf(_h -> _h.getId()==Integer.parseInt(getRequest().getParameter("dkey")));
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
