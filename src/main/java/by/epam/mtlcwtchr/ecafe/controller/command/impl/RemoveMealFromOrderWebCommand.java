package by.epam.mtlcwtchr.ecafe.controller.command.impl;

import by.epam.mtlcwtchr.ecafe.bean.Client;
import by.epam.mtlcwtchr.ecafe.bean.Meal;
import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.service.command.Command;
import by.epam.mtlcwtchr.ecafe.service.command.CommandType;
import by.epam.mtlcwtchr.ecafe.service.exception.ServiceException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveMealFromOrderWebCommand extends WebCommand {

    public RemoveMealFromOrderWebCommand(ServletRequest request, ServletResponse response){
        super(request, response);
    }

    @Override
    public void executeGet() throws ControllerException {

    }

    @Override
    public void executePost() throws ControllerException {
        try {
            final Client actor = (Client) ((HttpServletRequest) getRequest()).getSession().getAttribute("actor");
            actor.getCurrentOrder().removeMeal(Integer.parseInt(getRequest().getParameter("chosenMealId")));
            ((HttpServletRequest) getRequest()).getSession().removeAttribute("actor");
            ((HttpServletRequest) getRequest()).getSession().setAttribute("actor", actor);
            ((HttpServletResponse) getResponse()).sendRedirect(getRequest().getServletContext().getContextPath()+"/profile");
        } catch (IOException ex) {
            throw new ControllerException(ex);
        }
    }

}
