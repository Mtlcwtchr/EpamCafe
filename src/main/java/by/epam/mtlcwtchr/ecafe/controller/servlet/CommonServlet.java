package by.epam.mtlcwtchr.ecafe.controller.servlet;

import by.epam.mtlcwtchr.ecafe.controller.command.Command;
import by.epam.mtlcwtchr.ecafe.controller.command.WebCommandType;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.controller.filter.CommonUrlFilter;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;
import by.epam.mtlcwtchr.ecafe.logging.aspect.LoggingAspect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "ApplicationServlet", urlPatterns = "/app")
public class CommonServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(CommonServlet.class);

    @Override
    @ExceptionableBeingLogged
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            final Command webCommand = Command.of((WebCommandType) req.getAttribute(CommonUrlFilter.COMMAND_ATTRIBUTE), req, resp);
            webCommand.executeGet();
        } catch (ControllerException ex){
            ex.printStackTrace();
            logger.error(ex);
            try {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/something_went_wrong");
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }

    @Override
    @ExceptionableBeingLogged
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try{
            final Command webCommand = Command.of((WebCommandType) req.getAttribute(CommonUrlFilter.COMMAND_ATTRIBUTE), req, resp);
            webCommand.executePost();
        } catch (ControllerException ex){
            ex.printStackTrace();
            logger.error(ex);
            try {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/something_went_wrong");
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }


}