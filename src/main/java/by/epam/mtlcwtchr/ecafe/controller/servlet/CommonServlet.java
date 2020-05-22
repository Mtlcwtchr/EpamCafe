package by.epam.mtlcwtchr.ecafe.controller.servlet;

import by.epam.mtlcwtchr.ecafe.config.DependenciesLoader;
import by.epam.mtlcwtchr.ecafe.controller.command.WebCommand;
import by.epam.mtlcwtchr.ecafe.controller.command.WebCommandType;
import by.epam.mtlcwtchr.ecafe.controller.exception.ControllerException;
import by.epam.mtlcwtchr.ecafe.controller.filter.UrlFilter;
import by.epam.mtlcwtchr.ecafe.logging.annotation.ExceptionableBeingLogged;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ApplicationServlet", urlPatterns = "/app")
public class CommonServlet extends HttpServlet {

    @Override
    public void init() {
        System.out.println("Loaded " + DependenciesLoader.getInstance());
    }

    @Override
    @ExceptionableBeingLogged
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            final WebCommand webCommand = WebCommand.of((WebCommandType) req.getAttribute(UrlFilter.COMMAND_ATTRIBUTE), req, resp);
            webCommand.executeGet();
        } catch (ControllerException ex){
            ex.printStackTrace();
        }
    }

    @Override
    @ExceptionableBeingLogged
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try{
            final WebCommand webCommand = WebCommand.of((WebCommandType) req.getAttribute(UrlFilter.COMMAND_ATTRIBUTE), req, resp);
            webCommand.executePost();
        } catch (ControllerException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        System.out.println("Destroyed " + this);
    }
}