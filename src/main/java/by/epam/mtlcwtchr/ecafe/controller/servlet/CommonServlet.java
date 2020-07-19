package by.epam.mtlcwtchr.ecafe.controller.servlet;

import by.epam.mtlcwtchr.ecafe.config.StaticDataHandler;
import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.command.WebCommandType;
import by.epam.mtlcwtchr.ecafe.controller.localisationservice.LocalisationService;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.controller.filter.CommonUrlFilter;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ApplicationServlet", urlPatterns = "/app")
public class CommonServlet extends HttpServlet {

    @Override
    @ExceptionableBeingLogged
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            LocalisationService.setLocale(req, resp);
            final Command webCommand = Command.of((WebCommandType) req.getAttribute(CommonUrlFilter.COMMAND_ATTRIBUTE), req, resp);
            webCommand.executeGet();
        } catch (Throwable ex){
            ex.printStackTrace();
            StaticDataHandler.INSTANCE.getLOGGER().error(ex);
            try {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/something_went_wrong");
            } catch (IOException e) {
                StaticDataHandler.INSTANCE.getLOGGER().error(e);
            }
        }
    }

    @Override
    @ExceptionableBeingLogged
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try{
            LocalisationService.setLocale(req, resp);
            final Command webCommand = Command.of((WebCommandType) req.getAttribute(CommonUrlFilter.COMMAND_ATTRIBUTE), req, resp);
            webCommand.executePost();
        } catch (Throwable ex){
            ex.printStackTrace();
            StaticDataHandler.INSTANCE.getLOGGER().error(ex);
            try {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/something_went_wrong");
            } catch (IOException e) {
                StaticDataHandler.INSTANCE.getLOGGER().error(e);
            }
        }
    }


}